/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;

import entity.Account;
import entity.AccountDetail;
import entity.Cart;
import entity.Order;
import entity.OtherAddress;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDetailModel;
import model.OrderDetailModel;
import model.OrderModel;
import model.OtherAddressModel;
import model.ProductModel;

/**
 *
 * @author AnhDT45
 */
@WebServlet(name = "CheckOut", urlPatterns = {"/checkout"})
public class CheckOut extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String btnUpdate = request.getParameter("btnUpdate");
            String note = request.getParameter("note");

            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("currentLoginAccount");
            List<Cart> listOrder = (List<Cart>) session.getAttribute("listCart");
            double totalPrice = (double) session.getAttribute("totalPrice");

            Order order = new Order(account.getId(), totalPrice, note, 1);
            int orderId = new OrderModel().addOrder(order);

            if (orderId > 0) {
                boolean isCheckAddOrderDetail = new OrderDetailModel().addOrderDetail(listOrder, orderId);
                if (isCheckAddOrderDetail) {
                    boolean isCheckUpdateProductQuantity = new ProductModel().updateQuantityProduct(listOrder);

                    if (isCheckUpdateProductQuantity) {
                        AccountDetail accountDetail = new AccountDetailModel().getOneAccountDetail(account.getAccountDetailId());
                        OtherAddress otherAddress;
                        if (btnUpdate == null) {
                            otherAddress = new OtherAddress(accountDetail.getName(), accountDetail.getMobile(), accountDetail.getAddress(), orderId);
                        } else {
                            String name = request.getParameter("name");
                            String mobile = request.getParameter("mobile");
                            String address = request.getParameter("address");
                            otherAddress = new OtherAddress(name, mobile, address, orderId);
                        }
                        boolean isCheckAddAddress = new OtherAddressModel().addOtherAddress(otherAddress);
                        if (isCheckAddAddress) {
                            session.removeAttribute("listCart");
                            session.removeAttribute("totalPrice");
                            response.sendRedirect("thanks.jsp");
                        }
                    }
                }
            } else {
                response.sendRedirect("checkout.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
