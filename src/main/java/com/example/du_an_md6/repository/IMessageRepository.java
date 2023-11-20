package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "select * from message where send_acc_id = :sendId and receiver_acc_id = :receiveId or send_acc_id = :receiveId and receiver_acc_id = :sendId order by id", nativeQuery = true)
    List<Message> findAllByAccount(@Param("sendId") Long sendId, @Param("receiveId") Long receiveId);
}
