/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;

import entity.Account;
import entity.Order;
import entity.OrderDetail;
import entity.OtherAddress;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.OrderDetailModel;
import model.OrderModel;
import model.OtherAddressModel;
import utils.NumberUtil;

/**
 *
 * @author Shado
 */
@WebServlet(name = "GetAccountOrders", urlPatterns = {"/orders"})
public class GetAccountOrders extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("currentLoginAccount");
            
            ArrayList<Order> listOrder = new OrderModel().getOrderById(account.getId());
            
            String sOrderId = request.getParameter("id");
            int orderId;
            
            orderId = NumberUtil.getNumber(sOrderId, 0);
            
            if(orderId != 0){
                OtherAddress otherAddress = new OtherAddressModel().getOtherAddressByOrderId(orderId);
                ArrayList<OrderDetail> listOrderDetail = new OrderDetailModel().getOrderDetailByOrderId(orderId);
                request.setAttribute("addressDetail", otherAddress);
                request.setAttribute("listOrderDetail", listOrderDetail);
            }else{
                System.out.println("Check!!!");
            }
            
            request.setAttribute("listOrder", listOrder);
            request.getRequestDispatcher("user-orders.jsp").forward(request, response);
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
