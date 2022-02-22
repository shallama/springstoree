package com.example.springstore.unit;

import com.example.springstore.domain.dto.review.ReviewSearchRequest;
import com.example.springstore.domain.mapper.ReviewMapper;
import com.example.springstore.repository.ReviewRepository;
import com.example.springstore.service.DateService;
import com.example.springstore.service.ItemService;
import com.example.springstore.service.UserService;
import com.example.springstore.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceImplTest {
    @InjectMocks
    private ReviewServiceImpl reviewServiceImpl;
    @Mock
    private ReviewMapper reviewMapper;
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private UserService userService;
    @Mock
    private DateService dateService;
    @Mock
    private ItemService itemService;

    @Test
    public void getReviewListWithAllArguments(){
        Pageable pageable = PageRequest.of(0, 50);
        UUID userId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();
        ReviewSearchRequest searchRequest = ReviewSearchRequest.builder()
                .userId(userId)
                .itemId(itemId)
                .rate(5)
                .build();
        reviewServiceImpl.getReviewList(searchRequest, pageable);
    }
}
