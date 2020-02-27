package com.hotel.controller;

import com.hotel.model.*;
import com.hotel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class HomeController {
    @Autowired
    ImageService imageService;
    @Autowired
    TypeRoomService typeRoomService;
    @Autowired
    RoomService roomService;
    @Autowired
    BookingDetailService bookingDetailService;
    @Autowired
    BookingService bookingService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    BillService billService;
    @Autowired
    BillDetailService billDetailService;

    @GetMapping("/home")
    public ModelAndView showHome() {

        Iterable<TypeRoom> listRoom = typeRoomService.findAll();
        ModelAndView modelAndView = new ModelAndView("home", "listRoom", listRoom);
        modelAndView.addObject("rooms", roomService.findAll());
        modelAndView.addObject("booking", new BookingDetail());
        modelAndView.addObject("typeRooms",typeRoomService.findAll());
        modelAndView.addObject("bookingDetail", new BookingDetail(new Customer()));
        return modelAndView;
    }

    @GetMapping("/home/{id}")
    public ModelAndView showRoom(@PathVariable Long id) {
        Iterable<Image> images = imageService.findImagesByTypeRoom(typeRoomService.findById(id));
        Iterable<TypeRoom> listRoom = typeRoomService.findAll();
        ModelAndView modelAndView = new ModelAndView("room");
        modelAndView.addObject("images", images);
        List<String> descriptions = new ArrayList<>();
        for (Image image : images) {
            descriptions.add(image.getDescriptions());
        }
        modelAndView.addObject("listRoom", listRoom);
        modelAndView.addObject("descriptions",descriptions);
        return modelAndView;
    }

    @PostMapping("/create-booking")
    public ModelAndView createBooking(@ModelAttribute("booking") BookingDetail bookingDetail, @RequestParam Long rooms) {
        bookingDetailService.save(bookingDetail);
        ModelAndView modelAndView = new ModelAndView("home", "booking", new BookingDetail());
        modelAndView.addObject("message", "Book success");
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView showAdmin() {
        Iterable<Booking> bookings = bookingService.findAll();
        ModelAndView modelAndView = new ModelAndView("/admin/home", "bookings", bookings);
        Iterable<TypeRoom> typeRooms = typeRoomService.findAll();
        modelAndView.addObject("typeRooms", typeRooms);
        modelAndView.addObject("bookingDetail", new BookingDetail(new Customer()));
        return modelAndView;

    }

    @PostMapping("/create")
    public ModelAndView createBookingEmployee(@ModelAttribute("bookingDetail") BookingDetail bookingDetail, @RequestParam("roomIsBooking") List<String> roomIsBooking, @RequestParam("typeRoomId")List<String> typeRoomId, @RequestParam("price")List<String> price,@RequestParam("check")Long check,@RequestParam("name")String name,@RequestParam("email")String email,@RequestParam("address")String address,@RequestParam("identityCard")String identityCard) {
        Date checkIn = bookingDetail.getCheckInExpected();
        Date checkOut = bookingDetail.getCheckOutExpected();
        Customer customer= new Customer(identityCard,name,email,address);
        System.out.println(roomIsBooking.remove(""));
        System.out.println(price.remove(""));
        for(int i = 0; i<roomIsBooking.size(); i ++){
            if(roomIsBooking.get(i).equals("")){
                roomIsBooking.remove(roomIsBooking.get(i));
                price.remove(price.get(i));
                i--;
            }
        }
        Booking booking = new Booking();
        booking.setCheckIn(checkIn);
        booking.setCheckOut(checkOut);
        customerService.save(customer);
        Set<BookingDetail> bookingDetails = new HashSet<>();
        for (int i = 0; i < typeRoomId.size(); i++) {
            List<Room> rooms = (List<Room>) roomService.checkTypeRoom(checkIn, checkOut, Long.parseLong(typeRoomId.get(i)));
            if(!roomIsBooking.get(i).equals("")) {
                for (int j = 0; j < Long.parseLong(roomIsBooking.get(i)); j++) {
                    BookingDetail bookingDetailInLoop = new BookingDetail();
                    bookingDetailInLoop.setPrice(Long.parseLong(price.get(i)));
                    bookingDetailInLoop.setRoom(rooms.get(j));
                    bookingDetailInLoop.setCheckInExpected(checkIn);
                    bookingDetailInLoop.setCheckOutExpected(checkOut);
                    List<Customer> customerList = bookingDetailInLoop.getCustomers();
                    customerList.add(customer);
                    bookingDetailInLoop.setCustomers(customerList);
                    bookingDetails.add(bookingDetailInLoop);
                }
            }else {
                roomIsBooking.remove(roomIsBooking.get(i));
            }
        }
        booking.setCreateTime();
        booking.setCustomer(customer);
//        billService.save(new Bill(booking));
        bookingService.save(booking);
        List<Booking> bookings= bookingService.findCreateTime();

        billService.saveBillQuery(bookings.get(0).getId(),customer.getId());
        List<Customer> customers = customerService.findAllByQuery();
        for(BookingDetail b : bookingDetails){
            bookingDetailService.saveByQuery(checkIn,checkOut,customers.get(0).getId(),b.getRoom().getId(),bookings.get(0).getId(),b.getPrice());
            System.out.println(bookingDetailService.findIdBigger().get(0).getId());
//            customerService.updateDetailByQuery(bookingDetailService.findIdBigger().get(0).getId(),customer.getId());
            customer.setBookingDetail(bookingDetailService.findById(bookingDetailService.findIdBigger().get(0).getId()));
            customerService.save(customer);
        }
        if(check == 0){
            ModelAndView modelAndView = new ModelAndView("admin/createBooking", "bookingDetail", bookingDetail);
            modelAndView.addObject("roomIsBooking",roomIsBooking);
            modelAndView.addObject("typeRoomId",typeRoomId);
            modelAndView.addObject("message","Created Booking");
            return modelAndView;
        }
        Iterable<TypeRoom> listRoom = typeRoomService.findAll();
        ModelAndView modelAndView = new ModelAndView("home", "listRoom", listRoom);
        modelAndView.addObject("rooms", roomService.findAll());
        modelAndView.addObject("booking", new BookingDetail());
        modelAndView.addObject("typeRooms",typeRoomService.findAll());
        modelAndView.addObject("bookingDetail", new BookingDetail(new Customer()));
        return modelAndView;


    }


    @GetMapping("/create")
    public ModelAndView showCreate() {
        BookingDetail bookingDetail = new BookingDetail(new Customer());
        System.out.println(bookingDetail);
        return new ModelAndView("/admin/createBooking", "bookingDetail", bookingDetail);
//        select * from hotel.rooms as r inner join hotel.bookings as b on r.idRoom=b.room_id
//        where (b.checkinExpected >'2019-12-21' and b.checkOutExpected>'2019-12-22')
//        or (b.checkInExpected< '2019-12-22' and b.checkOutExpected<'2019-12-21')
    }
    @GetMapping("/admin/room")
    public ModelAndView showDashboard(){
        List<Room> rooms = (List<Room>) roomService.findAll();
        java.util.Date date =new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<Integer> list =new ArrayList<>();
        List<Room> roomIsBooking = (List<Room>) roomService.findAllRoomInDay(Date.valueOf(LocalDate.now()),Date.valueOf(formatter.format(date.getTime()+86400000)));
        for (int i =0; i<rooms.size();  i++) {
            for (int j=0; j<roomIsBooking.size(); j++) {
                if(rooms.get(i).getId().equals(roomIsBooking.get(j).getId())){
                    list.add(i);
                }
            }
            rooms.get(i).setStatus(true);
        }
        for (int k = 0; k<list.size(); k++ ){
            rooms.get(list.get(k)).setStatus(false);
        }
        ModelAndView modelAndView = new ModelAndView("admin/roomIsAvailable","rooms", rooms);
        modelAndView.addObject("typeRooms",typeRoomService.findAll());
        modelAndView.addObject("true",true);
        modelAndView.addObject("false",false);
        return modelAndView;
    }
    @GetMapping("edit-booking/{id}")
    public ModelAndView editBooking(@PathVariable("id")Long id ){
        java.util.Date date =new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<Room> rooms = (List<Room>) roomService.checkTypeRoom(Date.valueOf(LocalDate.now()), Date.valueOf(formatter.format(date.getTime()+86400000)), roomService.findById(id).getTypeRoom().getId());
        rooms.add(roomService.findById(id));
        BookingDetail bookingToday =  bookingDetailService.findBookingToday(Date.valueOf(LocalDate.now()),Date.valueOf(formatter.format(date.getTime()+86400000)),id);
        bookingToday.setCustomers(customerService.findByBookingDetail(bookingToday));
        ModelAndView modelAndView = new ModelAndView("admin/editBookingDetail","bookingToDay",bookingToday);
        modelAndView.addObject("rooms",rooms);
        return modelAndView;
    }
    @PostMapping("edit-booking")
    public ModelAndView editBooking(@ModelAttribute("bookingToDay")BookingDetail bookingToDay,@RequestParam("name1")String[] name,@RequestParam("identityCard1")String[] identityCard,@RequestParam("address1")String[] address,@RequestParam("email1")String[] email){
        for (int i =0; i<name.length;i++){
           Customer customerInLoop= new Customer(identityCard[i],name[i],email[i],address[i]);
           customerInLoop.setBookingDetail(bookingToDay);
           customerService.save(customerInLoop);
        }
        Booking booking =bookingService.findBookingByBookingDetailId(bookingToDay.getId());
        bookingToDay.setBooking(booking);
        bookingDetailService.save(bookingToDay);
        ModelAndView modelAndView = new ModelAndView("admin/editBookingDetail","bookingToDay",bookingToDay);
        modelAndView.addObject("message","Edit Room");
        return modelAndView;
    }
    @GetMapping("/bookingToDay")
    public ModelAndView showBookingToday(){
        java.util.Date date =new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<Booking> bookings =bookingService.findBookingToDay(Date.valueOf(LocalDate.now()),Date.valueOf(formatter.format(date.getTime()+86400000)));
        return new ModelAndView("admin/listBookingToDay","bookings",bookings);
    }
    @GetMapping("bookingToday/{id}")
    public ModelAndView showCheckOut(@PathVariable("id") Long id){
        Booking booking = bookingService.findById(id);
//        else {
//            List<Long> numberDateStay = new ArrayList<>();
//            for (BookingDetail bookingDetail: booking.getBookingDetailSet()) {
//                numberDateStay.add(bookingDetail.getNumberDate());
//
//            }
//            ModelAndView modelAndView = new ModelAndView("admin/BookingInDay","booking",booking);
//            modelAndView.addObject("products", productService.findAll());
//            modelAndView.addObject("bill",booking.getBill());
//            modelAndView.addObject("numberDateStay",numberDateStay);
//            return modelAndView;
//        }
        booking.setTotal(0L);
        List<Customer> customer = new ArrayList<>();
        //tính ngày ở tổng tiền phòng
        for (BookingDetail bookingDetail : booking.getBookingDetailSet()) {
            LocalDate dateNow = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate dateCheckIn = LocalDate.parse(bookingDetail.getCheckInExpected().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
            bookingDetail.setCheckOutExpected(Date.valueOf(LocalDate.now()));
            //nếu ngày ở chưa có, thì tính toán ngày ở
            if(bookingDetail.getNumberDate()==null) {
//                bookingDetail.setCheckOut(Date.valueOf(LocalDate.now()));
                //System.out.println(dateNow);
                //System.out.println(dateCheckIn);
                //tính ra ngày ở
                Duration diffInLoop = Duration.between(dateNow.atStartOfDay(), dateCheckIn.atStartOfDay());
                //System.out.println(diffInLoop.toDays());
                //System.out.println(diffInLoop);
                bookingDetail.setNumberDate((diffInLoop.toDays()+1));
                bookingDetail.setTotal(bookingDetail.getPrice()*bookingDetail.getNumberDate());
                booking.setTotal(booking.getTotal()+bookingDetail.getTotal());
                bookingDetail.setCustomers(customerService.findByBookingDetail(bookingDetail));
                bookingDetailService.save(bookingDetail);
                booking.getBill().setPriceRoom(booking.getTotal());
                booking.getBill().setCustomer(booking.getCustomer());
                booking.getBill().setNumberRoom((long) booking.getBookingDetailSet().size());
                billService.save(booking.getBill());
                bookingService.save(booking);
            }

        }
        Bill bill = booking.getBill();
        ModelAndView modelAndView = new ModelAndView("admin/BookingInDay","booking",booking);
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("products", productService.findAll());
        modelAndView.addObject("bill",bill);
        return modelAndView;
    }
    @PostMapping("/create-bill")
    public ModelAndView createBill(@RequestParam("idBill")Long idBill,@RequestParam("products")List<Long> products,@RequestParam("quantity")List<String> quantity){
        Bill bill = billService.findById(idBill);
        bill.setExtra(0L);

        Booking booking = bookingService.findBookingByBill(bill);
        bill.setTotal(booking.getTotal());
        //
        int sizeFN =quantity.size()-products.size();
        for (int i = 0; i<sizeFN; i++){
            quantity.remove("");
        }
        for (int j = 0; j<products.size(); j++){
            Product productInLoop = productService.findById(products.get(j));
            BillDetail billDetailInLoop = new BillDetail();
            billDetailInLoop.setBill(bill);
            billDetailInLoop.setProduct(productInLoop);
            billDetailInLoop.setPrice(productInLoop.getPrice());
            billDetailInLoop.setQuantity(Long.parseLong(quantity.get(j)));
            billDetailInLoop.setTotal(billDetailInLoop.getPrice()*billDetailInLoop.getQuantity());
            bill.setTotal(billDetailInLoop.getTotal()+bill.getTotal());
            billDetailService.save(billDetailInLoop);
            Set<BillDetail> billDetailSet = bill.getBillDetailSet();
            billDetailSet.add(billDetailInLoop);
            bill.setBillDetailSet(billDetailSet);
            Long totalExtra =bill.getExtra()+billDetailInLoop.getTotal();
            bill.setExtra(totalExtra);
        }
        System.out.println(bill.toString());
        billService.save(bill);
        ModelAndView modelAndView = new ModelAndView("admin/showBill","bill",bill);
        modelAndView.addObject("booking", booking);
        modelAndView.addObject("message","skjhskja");
        return modelAndView;
    }

    @GetMapping("/pay/{id}")
    public String pay(@PathVariable("id")Long id){
        Bill bill = billService.findById(id);
        Booking booking =bookingService.findBookingByBill(bill);
        booking.setStatus(true);
        bookingService.save( booking);
        Set<BookingDetail> bookings = booking.getBookingDetailSet();
        for (BookingDetail bookingDetail: bookings) {
            bookingDetail.setCheckOut(Date.valueOf(LocalDate.now()));
        }
        bill.setStatus(true);
        billService.save(bill);
        return "redirect:/bookingToDay";
    }
    @GetMapping("/listBill")
    public ModelAndView showBill(){
        List<Bill> bills= (List<Bill>) billService.findAll();
        List<Bill> billPayed = new ArrayList<>();


        for (Bill bill : bills) {
            if(bill.isStatus()){
                billPayed.add(bill);
            }
        }
        return new ModelAndView("admin/listBill","bills",billPayed);
    }
    @GetMapping("view/{id}")
    public ModelAndView viewBill(@PathVariable("id")Long id){
        Bill bill = billService.findById(id);
        Booking booking = bookingService.findBookingByBill(bill);
        ModelAndView modelAndView = new ModelAndView("admin/showBill","bill",billService.findById(id));
        modelAndView.addObject("booking", booking);
        return modelAndView;
    }
    @PostMapping("/searchById")
    public ModelAndView showRoom(@RequestParam("search") String search){
        Booking booking = bookingService.findBookingByCustomerIdentityCard(search);
        return new ModelAndView("admin/resultSearch","booking",booking);
    }

}
