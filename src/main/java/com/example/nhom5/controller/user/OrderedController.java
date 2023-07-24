package com.example.nhom5.controller.user;

import com.example.nhom5.config.Config;
import com.example.nhom5.converter.OrderedConverter;
import com.example.nhom5.domain.Ordered;
import com.example.nhom5.domain.OrderedDetail;
import com.example.nhom5.domain.Product;
import com.example.nhom5.domain.User;
import com.example.nhom5.model.CartItem;
import com.example.nhom5.model.CartManager;
import com.example.nhom5.model.OrderedDto;
import com.example.nhom5.model.PaymentResDto;
import com.example.nhom5.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("api/user/orders")

public class OrderedController {
    @Autowired
    OrderedService orderedService;
    @Autowired
    OrderedDetailService orderedDetailService;
    @Autowired
    StockService stockService;
    @Autowired
    CartManager cartManager;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderedConverter orderedConverter;
    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping("/list")
    @ResponseBody
    public List<OrderedDto> getListOrderd() {
        return orderedService.getAllOrdered();
    }

    @PostMapping("/checkout-code")
    public ResponseEntity<?> checkoutCode(@RequestHeader("Authorization") String token) {
        int userId;
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7); // Lấy phần token sau "Bearer "
            User user = userService.findByToken(jwtToken);
            if (user == null) {
                // Return bad request if the user is not found with the given token
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
            }
            userId = user.getUserId();
        } else {
            // Return bad request if the token is not provided or in an invalid format
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
        }
        User user = userService.findUserById(userId);

        String status = "Success";
        double totalPrice = cartManager.totalPrice();
        // Tạo đối tượng Order
        Ordered order = new Ordered();
        order.setUser(user);
        order.setStatus(status);
        order.setTotalPrice(totalPrice);
        // Lấy ngày giờ hiện tại
        Date orderDate = new Date();
        order.setOrderDate(orderDate);
        // OrderedDto orderedDto = orderedConverter.toDto(orderedService.save(order));
        orderedService.save(order);
        // Tạo đối tượng orderedDetails
        List<CartItem> cartItems = cartManager.getCartItems();
        List<OrderedDetail> orderedDetails = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderedDetail orderedDetail = new OrderedDetail();
            orderedDetail.setQuantityOrder(cartItem.getQuantity());
            orderedDetail.setColorName(cartItem.getColorName());
            orderedDetail.setSizeName(cartItem.getSizeName());
            orderedDetail.setUnitPrice(cartItem.getUnitPrice());
            int productId = cartItem.getProductId();
            Product product = productService.findProductById(productId);
            orderedDetail.setProduct(product);
            orderedDetail.setOrder(order);
            orderedDetails.add(orderedDetail);
        }

        orderedDetailService.addOrderedDetails(orderedDetails);
        // Cập nhật số lượng trong kho hàng

        // Gửi email đặt hàng thành công cho khách hàng
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        System.out.println(user.getEmail());
        message.setSubject("Order Successfully!");
        String mes = "";
        mes += "\n Dear Mr/Mrs " + user.getFirstName() + user.getLastName() + ",";
        mes += "\n Thank you for visiting us and making your purchase";

        mes += "\n Your order includes: ";
        int index = 1;
        for (CartItem cartItem : cartItems) {
            mes += "\n" + index + ". " + cartItem.getProductName() + ", quantity: " + cartItem.getQuantity();
            index++;
        }
        mes += "\n Total order price: " + order.getTotalPrice() + "$";
        mes += "\n Your order for another quarter has been processed and will ship in the next few days";
        message.setText(mes);
        this.javaMailSender.send(message);
        stockService.updateStockQuantity(cartItems);
        cartManager.removeAllCart();
        return ResponseEntity.ok().body("Checkout success!");
    }
    //----------------------------------Checkout online---------------------------------------------
    @PostMapping("/checkout-online")
    @ResponseBody
    public ResponseEntity<?> checkoutOnline(@RequestHeader("Authorization") String token) {
        int userId;
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7); // Lấy phần token sau "Bearer "
            User user = userService.findByToken(jwtToken);
            if (user == null) {
                // Return bad request if the user is not found with the given token
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
            }
            userId = user.getUserId();
        } else {
            // Return bad request if the token is not provided or in an invalid format
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
        }
        User user = userService.findUserById(userId);

        String status = "Success";
        double totalPrice = cartManager.totalPrice();
        // Tạo đối tượng Order
        Ordered order = new Ordered();
        order.setUser(user);
        order.setStatus(status);
        order.setTotalPrice(totalPrice);
        // Lấy ngày giờ hiện tại
        Date orderDate = new Date();
        order.setOrderDate(orderDate);
        // OrderedDto orderedDto = orderedConverter.toDto(orderedService.save(order));
        orderedService.save(order);
        // Tạo đối tượng orderedDetails
        List<CartItem> cartItems = cartManager.getCartItems();
        List<OrderedDetail> orderedDetails = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderedDetail orderedDetail = new OrderedDetail();
            orderedDetail.setQuantityOrder(cartItem.getQuantity());
            orderedDetail.setColorName(cartItem.getColorName());
            orderedDetail.setSizeName(cartItem.getSizeName());
            orderedDetail.setUnitPrice(cartItem.getUnitPrice());
            int productId = cartItem.getProductId();
            Product product = productService.findProductById(productId);
            orderedDetail.setProduct(product);
            orderedDetail.setOrder(order);
            orderedDetails.add(orderedDetail);
        }

        orderedDetailService.addOrderedDetails(orderedDetails);
        // Cập nhật số lượng trong kho hàng

        // Gửi email đặt hàng thành công cho khách hàng
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        System.out.println(user.getEmail());
        message.setSubject("Order Successfully!");
        String mes = "";
        mes += "\n Dear Mr/Mrs " + user.getFirstName() + user.getLastName() + ",";
        mes += "\n Thank you for visiting us and making your purchase";

        mes += "\n Your order includes: ";
        int index = 1;
        for (CartItem cartItem : cartItems) {
            mes += "\n" + index + ". " + cartItem.getProductName() + ", quantity: " + cartItem.getQuantity();
            index++;
        }
        mes += "\n Total order price: " + order.getTotalPrice() + "$";
        mes+="\n Thank you for your payment!";
        mes += "\n Your order for another quarter has been processed and will ship in the next few days";
        message.setText(mes);
        this.javaMailSender.send(message);
        stockService.updateStockQuantity(cartItems);
        cartManager.removeAllCart();
        return ResponseEntity.ok().body("Checkout success!");
    }

    //Tích hợp thanh toán VNPay
    @PostMapping("/create_payment")
    public ResponseEntity<?> createPayment() throws UnsupportedEncodingException {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        long amount = (long) (cartManager.totalPrice() * 2400000);
        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_TmnCode = Config.vnp_TmnCode;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_OrderType", "ORDER_TYPE");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);

        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", "13.160.92.202");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        PaymentResDto paymentResDto = new PaymentResDto();
        paymentResDto.setStatus("ok");
        paymentResDto.setMessage("Successfully");
        paymentResDto.setURL(paymentUrl);

        return ResponseEntity.ok().body(paymentResDto);
    }


//    HaiNam code
    @GetMapping("/statisticorderbymonth")
    public ResponseEntity<?> get_All_Orders_By_Month(){
        Map<String,Object> output = new HashMap<>();
        output.put("status",HttpStatus.OK.value());
        output.put("orderstatistics",orderedService.getStatisticOrderByMonth());
        return  new ResponseEntity<>(output,HttpStatus.OK);
    }

    @GetMapping("/getsumorders")
    public ResponseEntity<?> get_Sum_Orders(){
        Map<String,Object> output = new HashMap<>();
        output.put("status",HttpStatus.OK.value());
        output.put("sumorder",orderedService.getSumOrders());
        return  new ResponseEntity<>(output,HttpStatus.OK);
    }
}
