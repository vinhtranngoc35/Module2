package com.hotel.repository;

import com.hotel.model.prototype.ICheckTypeRoom;
import com.hotel.model.Room;
import com.hotel.model.TypeRoom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

public interface RoomRepository extends PagingAndSortingRepository<Room,Long> {
    Iterable<Room> findAllByTypeRoom(TypeRoom typeRoom);
//    @Transactional
//    @Modifying
//    @Query(value = "select count(*) as count,typeroom.name as name,typeroom.idTypeRoom as idTypeRoom from rooms inner join typerooms as typeroom on rooms.typeroom_id=typeroom.idTyperoom\n" +
//            "\n" +
//            " where rooms.idRoom not in(\n" +
//            "select t.idRoom\n" +
//            "from(\n" +
//            "select  r.name,r.idRoom,r.typeroom_id  from rooms as r inner join bookingdetails as b on r.idRoom =b.room_id \n" +
//            "where ((b.checkInExpected >= :checkIn and b.checkInExpected <= :checkOut)\n" +
//            "\t or (b.checkOutExpected >= :checkIn and b.checkOutExpected<= :checkOut))\n" +
//            "     group by r.idRoom) t) group by typeroom_id;\n;",nativeQuery = true)
    @Query(value = "select count(*) as count, typeroom.price, typeroom.name as name,typeroom.idTypeRoom as idTypeRoom from rooms inner join typerooms as typeroom on rooms.typeroom_id=typeroom.idTyperoom where rooms.idRoom not in(select t.idRoom from(select  r.name,r.idRoom,r.typeroom_id  from rooms as r inner join bookingdetails as b on r.idRoom =b.room_id where (b.checkInExpected <:checkOut  and b.checkOutExpected> :checkIn and b.checkOut is null ) group by r.idRoom) t) group by typeroom_id" ,nativeQuery = true)
//    List checkRoom(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut);
//    @SqlResultSetMapping(name = "checkTypeRoom", entities = {
//            @FieldResult(name="id",column = "id"),
//            @FieldResult(name = "name", column = "name"),
//            @FieldResult(name = "count", column = "count")
//})
    Iterable<ICheckTypeRoom> checkRoom(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut);



    @Query(value = "select rooms.idRoom,rooms.name,rooms.status,rooms.typeroom_id from rooms where rooms.idRoom not in(select t.idRoom from(select  r.name,r.idRoom,r.typeroom_id  from rooms as r inner join bookingdetails as b on r.idRoom =b.room_id where ((b.checkInExpected < :checkOut and b.checkOutExpected> :checkIn and b.checkOut is null)) group by r.idRoom) t) and typeroom_id =:id ;" ,nativeQuery = true)
    Iterable<Room> checkTypeRoom(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut,@Param("id") Long id);


    @Transactional
    @Modifying
    @Query("select r from Room as r inner join BookingDetail as b on r.id=b.room.id\n" +
            "where ((b.checkInExpected < :checkIn and b.checkOutExpected > :checkOut and b.checkOut IS not null)) and r.typeRoom.id=:id group by (r.id)\n" +
            " \n")
    Iterable<Room> findAllRoomIsBooking(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut,@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("select r from Room as r inner join BookingDetail as b on r.id=b.room.id\n" +
            "where ((b.checkInExpected < :checkOut and b.checkOutExpected > :checkIn and b.checkOut Is null)) group by (r.id)\n" +
            " \n")
    Iterable<Room> findAllRoomInDay(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut);
}
