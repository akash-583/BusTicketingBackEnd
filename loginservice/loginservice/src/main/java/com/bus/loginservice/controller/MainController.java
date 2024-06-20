package com.bus.loginservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.loginservice.model.BookingModel;
import com.bus.loginservice.model.BusBookingVo;
import com.bus.loginservice.model.BusModel;
import com.bus.loginservice.model.CustomerIssueModel;
import com.bus.loginservice.model.Login;
import com.bus.loginservice.model.UserIssueVO;
import com.bus.loginservice.proxy.BookingProxy;
import com.bus.loginservice.proxy.BusProxy;
import com.bus.loginservice.proxy.IssueProxy;
import com.bus.loginservice.service.LoginServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/registration/authorization")
public class MainController {
    @Autowired
    private LoginServiceImpl serviceImpl;

    @Autowired
    private BusProxy proxy;

    @Autowired
    private BookingProxy bookingProxy;

    @Autowired
    private IssueProxy issueProxy;



    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<Login>> getAllUsers(){

        return ResponseEntity.ok(serviceImpl.getAllUsers());
    }

    @GetMapping("/getbyUsername/{username}")
    public ResponseEntity<Login> getbyUserName(@PathVariable String username){
        return ResponseEntity.ok(serviceImpl.getByUsername(username));
    }

    @PreAuthorize("hasAnyAuthority('User', 'Admin')")
    @PutMapping("/updatethepassword/{username}")
    public ResponseEntity<Login> updatePassword(@PathVariable String username, @RequestBody Login Login ){
        return ResponseEntity.ok(serviceImpl.updatePassword(username, Login));
    }

    @PutMapping("/updateUserDetails/{username}")
    public ResponseEntity<Login> updateByUserDetails(@PathVariable String username, @RequestBody Login login){
        return ResponseEntity.ok(serviceImpl.updateByUserDetails(username, login));
    }
    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username){
        serviceImpl.DeleteUser(username);
        return ResponseEntity.ok("User Deleted Successfully");
    }



    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addbus")
    public String addbus(@RequestBody BusModel busModel) {

        proxy.addBusModel(busModel);

        return "Bus with No: "+busModel.getBusNo()+" have been added Successfully";
    }


    //	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
    @GetMapping("/viewallbuses")
    public List<BusModel> getAllBuses(){
     return proxy.getAllBuses();
    }

    @PreAuthorize("hasAnyAuthority('User', 'Admin')")
    @GetMapping("/viewbusbyno/{busNo}")
    public BusModel getBusByNo(@PathVariable String busNo) {

        return proxy.getBusById(busNo);
    }

  
    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deletebus/{busNo}")
    public String deleteBus(@PathVariable String busNo) {
        proxy.deleteBus(busNo);
        return "Bus with no."+busNo+" have been deleted";
    }

    //	@PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updatebusbyid/{busNo}")
    public String updateBus(@PathVariable String busNo,@RequestBody BusModel busModel) {
        proxy.updateBus(busNo, busModel);
        return "Bus with no."+busNo+" have been updated";
    }

    //	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
    @GetMapping("/findbetween/{source}/{destination}")
    public List<BusModel> findByLocation(@PathVariable String source, @PathVariable String destination){
        return proxy.findByLocation(source, destination);
    }




    @PreAuthorize("hasAuthority('User')")
    @PostMapping("/booking/{username}")
    public BookingModel bookTicket(@RequestBody BookingModel booking,@PathVariable String username) {
		booking.setUsername(username);

        return bookingProxy.bookTicket(booking);

    }

    @GetMapping("/ViewAllBookings")
    public List<BookingModel> viewAllBookings() {
        return bookingProxy.viewAllBookings();
    }

    //	@PreAuthorize("hasAuthority('User')")
    @DeleteMapping("/deleteTicket/{ticketNo}")
    public String cancelTicket(@PathVariable String ticketNo) {
        return bookingProxy.cancelTicket(ticketNo);
    }

    @PreAuthorize("hasAnyAuthority('User', 'Admin')")
    @GetMapping("/ViewTicketByticketNo/{ticketNo}")
    public BookingModel viewByticketNo(@PathVariable String ticketNo) {
        return bookingProxy.viewByticketNo(ticketNo);
    }

    	 @PreAuthorize("hasAuthority('User')")
    @GetMapping("/viewByUserName/{username}")
    public List<BookingModel> viewByUserName(@PathVariable String username){
        return bookingProxy.viewByUserName(username);
    }

    @PreAuthorize("hasAnyAuthority('User', 'Admin')")
    @GetMapping("/ViewBookingTicketByItsBusAndTotalCost/{ticketNo}")
    public BusBookingVo getBookingTicketByItsBusAndTotalCost(@PathVariable String ticketNo) {
        return bookingProxy.getBookingTicketByItsBusAndTotalCost(ticketNo);
    }




    @PreAuthorize("hasAuthority('User')")
    @PostMapping("/issue/addIssue/{username}")
    public String addissue(@RequestBody CustomerIssueModel helpModel, @PathVariable String username) {
        helpModel.setUsername(username);
        return issueProxy.addissue(helpModel);
    }

    @PreAuthorize("hasAnyAuthority('User', 'Admin')")
    @GetMapping("/issue/getAllIssues")
    public List<CustomerIssueModel> getAllIssues(){
        return issueProxy.getAllIssues();
    }


    @PreAuthorize("hasAnyAuthority('User', 'Admin')")
    @GetMapping("/issue/getByUsername/{username}")
    public UserIssueVO getByUsername(@PathVariable String username) {
        return issueProxy.getByUsername(username);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/issue/update/{issue}")
    public CustomerIssueModel updateIssue(@RequestBody CustomerIssueModel helpModel, @PathVariable String issue) {
        return issueProxy.updateIssue(helpModel, issue);
    }

    @PreAuthorize("hasAnyAuthority('User', 'Admin')")
    @GetMapping("/issue/getUserissues/{username}")
    public List<CustomerIssueModel> getUserissues(@PathVariable String username){
        return issueProxy.getUserissues(username);
    }
}
