package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "select * from notification where id_rec_acc = :rec_acc and id_send_acc != :rec_acc order by id_notification desc ", nativeQuery = true)
    List<Notification> findAllByRecAcc(@Param("rec_acc") Long id_rec_acc);
}
