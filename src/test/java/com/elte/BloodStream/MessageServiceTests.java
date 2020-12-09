package com.elte.BloodStream;

import com.elte.BloodStream.model.Donor;
import com.elte.BloodStream.model.Message;
import com.elte.BloodStream.model.News;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.repository.MessageRepository;
import com.elte.BloodStream.repository.NewsRepository;
import com.elte.BloodStream.service.MessageService;
import com.elte.BloodStream.service.NewsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTests {

        @Autowired
        private MessageService messageService;

        @MockBean
        private MessageRepository messageRepository;
        @MockBean
        private DonorRepository donorRepository;

       Message message = null;
       Message message2 = null;
       Message message3 = null;

        @Before
        public void init() {
            message  = Mockito.mock(Message.class);
            message2 = Mockito.mock(Message.class);
            message3 = Mockito.mock(Message.class);
        }
        @After
        public void cleanUp() {
            System.out.println(message);
            message = null;
            message2 = null;
            message3 = null;
        }

        @Test
        public void getAllMessagesTest() {
            //given
            //when
            when(messageRepository.findAll()).thenReturn(
                    Stream.of( message, message2, message3 ).collect(Collectors.toList())
            );
            //then
            assertEquals(3, messageRepository.findAll().size());
            verify(messageRepository, times(1)).findAll();
        }

        @Test
        public void getMessageTest() {
            //given
            int msgID = 1;
            Optional<Message> oMsg = Optional.of(message);
            //when
            when(message.getMsgId()).thenReturn(msgID);
            when(messageRepository.findByMsgId(message.getMsgId())).thenReturn(oMsg);
            //then
            assertEquals(new ResponseEntity(message, HttpStatus.OK), messageService.getMessage(message.getMsgId()));
            verify(messageRepository, times(1)).findByMsgId(message.getMsgId());
        }



        @Test
        public void getDonorMessagesTest() {
            //given
            int msgID = 1;
            int donorID = 1;
            when(messageRepository.findAllByDonorId(donorID)).thenReturn(
                    Stream.of( message, message2 ).collect(Collectors.toList())
            );
            assertEquals(2, ((Collection<Message>) messageService.getDonorMessages(donorID)).size());
            verify(messageRepository, times(1)).findAllByDonorId(donorID);
        }



        @Test
        public void createMessageTest() {
            //given
            int donorID = 1;
            Donor donor = Mockito.mock(Donor.class);
            //when
            Mockito.when(donorRepository.findById( donorID )).thenReturn( Optional.of(donor));
            Mockito.when(messageRepository.save(any(Message.class))).thenReturn(message);
            //then
            assertEquals(new ResponseEntity(message, HttpStatus.OK), messageService.createMessage(message, donorID));

            verify(donorRepository, times(1)).findById(donorID);
            verify(messageRepository, times(1)).save(any(Message.class));
        }

        @Test
        public void setMessageSeenTest() {
            //given
            int msgID = 1;
            Optional<Message> oMsg = Optional.of(message);
            //when
            when(message.getMsgId()).thenReturn(msgID);
            when(message2.getMsgId()).thenReturn(msgID);
            when(messageRepository.findByMsgId(message.getMsgId())).thenReturn(oMsg);
            //then
            assertEquals(new ResponseEntity(HttpStatus.OK), messageService.setSeen(message2.getMsgId(), message2));
            verify(messageRepository, times(1)).findByMsgId(message.getMsgId());
        }
}
