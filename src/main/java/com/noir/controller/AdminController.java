package com.noir.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noir.dto.request.AdminLoginRequest;
import com.noir.dto.request.CreateStaffRequest;
import com.noir.dto.request.IngredientRequest;
import com.noir.dto.request.MenuItemRequest;
import com.noir.dto.request.OrderStatusUpdateRequest;
import com.noir.dto.request.PromotionRequest;
import com.noir.dto.request.PromotionStatusRequest;
import com.noir.dto.request.ReservationAdminUpdateRequest;
import com.noir.dto.request.SpecialPlateRequest;
import com.noir.dto.request.StaffUpdateRequest;
import com.noir.dto.request.TableRequest;
import com.noir.dto.request.UpdateAdminProfileRequest;
import com.noir.service.AdminAuthService;
import com.noir.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminAuthService adminAuthService;
    private final AdminService adminService;

    public AdminController(AdminAuthService adminAuthService, AdminService adminService) {
        this.adminAuthService = adminAuthService;
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AdminLoginRequest req) {
        return ResponseEntity.ok(Map.of("success", true,
                "data", adminAuthService.authenticate(req)));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest req) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminAuthService.getAdminProfile()));
    }

    @RequestMapping(value = "/me", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UpdateAdminProfileRequest req) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminAuthService.updateAdminProfile(req)));
    }

    @PutMapping("/me/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody com.noir.dto.request.ChangeAdminPasswordRequest req) {
        adminAuthService.changeAdminPassword(req);
        return ResponseEntity.ok(Map.of("success", true, "message", "Password updated successfully"));
    }

    @GetMapping("/overview")
    public ResponseEntity<?> overview() {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.getOverview()));
    }

    // ---------------------------------------------------------------
    // Menu
    // ---------------------------------------------------------------
    @GetMapping("/menu")
    public ResponseEntity<?> listMenu() {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.listMenu()));
    }

    @PostMapping("/menu")
    public ResponseEntity<?> createMenu(@Valid @RequestBody MenuItemRequest req) {
        return ResponseEntity.status(201).body(Map.of("success", true, "data", adminService.createMenuItem(req)));
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable int id, @Valid @RequestBody MenuItemRequest req) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.updateMenuItem(id, req)));
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable int id) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.deleteMenuItem(id)));
    }

    // ---------------------------------------------------------------
    // Reservations
    // ---------------------------------------------------------------
    @GetMapping("/reservations")
    public ResponseEntity<?> listReservations() {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.listReservations()));
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<?> getReservation(@PathVariable String id) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.getReservation(id)));
    }

    @PatchMapping("/reservations/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable String id,
            @RequestBody ReservationAdminUpdateRequest req) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.updateReservation(id, req)));
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable String id) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.deleteReservation(id)));
    }

    // ---------------------------------------------------------------
    // Orders
    // ---------------------------------------------------------------
    @GetMapping("/orders")
    public ResponseEntity<?> listOrders() {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.listOrders()));
    }

    @DeleteMapping("/orders/{orderNumber}")
    public ResponseEntity<?> deleteOrder(@PathVariable String orderNumber) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.deleteOrder(orderNumber)));
    }

    /** Admin can set any order status */
    @PatchMapping("/orders/{orderNumber}/status")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable String orderNumber,
            @Valid @RequestBody OrderStatusUpdateRequest req) {
        return ResponseEntity.ok(Map.of("success", true,
                "data", adminService.updateOrderStatus(orderNumber, req)));
    }

    // ---------------------------------------------------------------
    // Reviews
    // ---------------------------------------------------------------
    @GetMapping("/reviews")
    public ResponseEntity<?> listReviews() {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.listReviews()));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable String id) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.deleteReview(id)));
    }

    // ---------------------------------------------------------------
    // Subscribers
    // ---------------------------------------------------------------
    @GetMapping("/subscribers")
    public ResponseEntity<?> listSubscribers() {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.listSubscribers()));
    }

    @GetMapping("/subscribers/{id}")
    public ResponseEntity<?> getSubscriber(@PathVariable String id) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.getSubscriber(id)));
    }

    @DeleteMapping("/subscribers/{id}")
    public ResponseEntity<?> deleteSubscriber(@PathVariable String id) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.deleteSubscriber(id)));
    }

    // ---------------------------------------------------------------
    // Promotions
    // ---------------------------------------------------------------
    @GetMapping("/promotions")
    public ResponseEntity<?> listPromotions() {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.listPromotions()));
    }

    @PostMapping("/promotions")
    public ResponseEntity<?> createPromotion(@Valid @RequestBody PromotionRequest req) {
        return ResponseEntity.status(201).body(Map.of("success", true, "data", adminService.createPromotion(req)));
    }

    @PutMapping("/promotions/{id}")
    public ResponseEntity<?> updatePromotion(@PathVariable String id, @Valid @RequestBody PromotionRequest req) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.updatePromotion(id, req)));
    }

    @PatchMapping("/promotions/{id}/status")
    public ResponseEntity<?> updatePromotionStatus(@PathVariable String id,
            @Valid @RequestBody PromotionStatusRequest req) {
        return ResponseEntity.ok(Map.of("success", true,
                "data", adminService.updatePromotionStatus(id, req.getStatus())));
    }

    // ---------------------------------------------------------------
    // Staff management
    // ---------------------------------------------------------------
    @GetMapping("/staff")
    public ResponseEntity<?> listStaff() {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.listStaff()));
    }

    @PostMapping("/staff")
    public ResponseEntity<?> createStaff(@Valid @RequestBody CreateStaffRequest req) {
        return ResponseEntity.status(201).body(Map.of("success", true, "data", adminService.createStaff(req)));
    }

    @PutMapping("/staff/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable String id,
            @Valid @RequestBody StaffUpdateRequest req) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.updateStaff(id, req)));
    }

    @DeleteMapping("/staff/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable String id) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.deleteStaff(id)));
    }

    // ---------------------------------------------------------------
    // Special plates (admin has full control)
    // ---------------------------------------------------------------
    @GetMapping("/special-plates")
    public ResponseEntity<?> listSpecialPlates() {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.listSpecialPlates()));
    }

    @GetMapping("/ingredients")
    public ResponseEntity<?> listIngredients() {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.listIngredients()));
    }
 @GetMapping("/ingredients/low-stock")  // ✅ ICI, avant /{id}
public ResponseEntity<?> lowStockAlerts() {
    return ResponseEntity.ok(Map.of("success", true, "data", adminService.getLowStockIngredients()));
}

    @PostMapping("/ingredients")
    public ResponseEntity<?> createIngredient(@Valid @RequestBody IngredientRequest req) {
        return ResponseEntity.status(201).body(Map.of("success", true, "data", adminService.createIngredient(req)));
    }

    @PutMapping("/ingredients/{id}")
    public ResponseEntity<?> updateIngredient(@PathVariable String id,
            @Valid @RequestBody IngredientRequest req) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.updateIngredient(id, req)));
    }

    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable String id) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.deleteIngredient(id)));
    }

@GetMapping("/invoices")
public ResponseEntity<?> listInvoices() {
    return ResponseEntity.ok(Map.of("success", true, "data", adminService.listInvoices()));
}
    @GetMapping("/tables")
    public ResponseEntity<?> listTables() {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.listTables()));
    }

    @PostMapping("/tables")
    public ResponseEntity<?> createTable(@Valid @RequestBody TableRequest req) {
        return ResponseEntity.status(201).body(Map.of("success", true, "data", adminService.createTable(req)));
    }

    @PutMapping("/tables/{id}")
    public ResponseEntity<?> updateTable(@PathVariable String id,
            @Valid @RequestBody TableRequest req) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.updateTable(id, req)));
    }

    @DeleteMapping("/tables/{id}")
    public ResponseEntity<?> deleteTable(@PathVariable String id) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.deleteTable(id)));
    }

    @PostMapping("/special-plates")
    public ResponseEntity<?> createSpecialPlate(@Valid @RequestBody SpecialPlateRequest req) {
        return ResponseEntity.status(201).body(Map.of("success", true,
                "data", adminService.createSpecialPlate(req)));
    }

    @PutMapping("/special-plates/{id}")
    public ResponseEntity<?> updateSpecialPlate(@PathVariable String id,
            @Valid @RequestBody SpecialPlateRequest req) {
        return ResponseEntity.ok(Map.of("success", true,
                "data", adminService.updateSpecialPlate(id, req)));
    }

    @DeleteMapping("/special-plates/{id}")
    public ResponseEntity<?> deleteSpecialPlate(@PathVariable String id) {
        return ResponseEntity.ok(Map.of("success", true, "data", adminService.deleteSpecialPlate(id)));
    }

    // ---------------------------------------------------------------
    // Chef recommendation — admin can also do this
    // ---------------------------------------------------------------
    @PostMapping("/recommend/{menuItemId}")
    public ResponseEntity<?> recommend(@PathVariable int menuItemId) {
        return ResponseEntity.ok(Map.of("success", true,
                "data", adminService.recommendMenuItem(menuItemId, "admin")));
    }
}
