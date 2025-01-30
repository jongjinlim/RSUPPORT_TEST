package com.example.rsupporttest;

import com.example.rsupporttest.services.attach.domain.Attach;
import com.example.rsupporttest.services.attach.repository.AttachRepository;
import com.example.rsupporttest.services.attach.service.AttachService;
import com.example.rsupporttest.services.notice.domain.Notice;
import com.example.rsupporttest.services.notice.domain.NoticeRequest;
import com.example.rsupporttest.services.notice.domain.NoticeResponse;
import com.example.rsupporttest.services.notice.repository.NoticeRepository;
import com.example.rsupporttest.services.notice.service.NoticeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoticeServiceTest {

	@Mock
	private NoticeRepository noticeRepository;
	@Mock
	private AttachRepository attachRepository;
	@InjectMocks
	private NoticeService noticeService;

	private Notice notice;
	private NoticeRequest noticeRequest;
	private Attach attach;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		notice = Notice.builder()
				.title("Test Notice")
				.contents("This is a test content.")
				.startDate(LocalDateTime.now())
				.endDate(LocalDateTime.now().plusDays(5))
				.createdBy("sugar")
				.build();

		noticeRequest = new NoticeRequest("Test Notice",
				"This is a test content",
				LocalDateTime.now(),
				LocalDateTime.now().plusDays(5),
				"sugar");

		attach = Attach.builder()
				.noticeId(1L)
				.fileName("test.jpg")
				.fileUrl("http://test.com")
				.build();
	}

	@Test
	void testCreateNotice() throws IOException {
		// Given
		when(noticeRepository.save(any(Notice.class))).thenReturn(notice);

		// When
		Long noticeId = noticeService.createNotice(noticeRequest.toCreateNotice(), List.of(mock(MultipartFile.class)));

		// Then
		assertNotNull(noticeId);
		verify(noticeRepository, times(1)).save(any(Notice.class));
	}

	@Test
	void testUpdateNotice() {
		// Given
		when(noticeRepository.findById(anyLong())).thenReturn(Optional.of(notice));
		when(noticeRepository.save(any(Notice.class))).thenReturn(notice);

		// When
		Notice updatedNotice = noticeService.updateNotice(
				noticeRequest.toUpdateNotice(1L),
				List.of(mock(MultipartFile.class)),
				List.of(1L,2L)
		);

		// Then
		assertNotNull(updatedNotice);
		assertEquals("Updated Title", updatedNotice.getTitle());
		verify(noticeRepository, times(1)).save(any(Notice.class));
	}

	@Test
	void testDeleteNotice() {
		// Given
		when(noticeRepository.findById(anyLong())).thenReturn(Optional.of(notice));
		when(attachRepository.findByNoticeId(anyLong())).thenReturn(Arrays.asList(attach));

		// When
		noticeService.deleteNotice(1L);

		// Then
		verify(attachRepository, times(1)).deleteAllInBatch(anyList());
		verify(noticeRepository, times(1)).delete(any(Notice.class));
	}

	@Test
	void testGetNoticeById() {
		// Given
		when(noticeRepository.findById(anyLong())).thenReturn(Optional.of(notice));

		// When
		Notice foundNotice = noticeService.getNotice(1L);

		// Then
		assertNotNull(foundNotice);
		assertEquals(1L, foundNotice.getId());
	}
}

