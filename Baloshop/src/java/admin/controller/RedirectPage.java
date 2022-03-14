/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import entity.Account;
import entity.Category;
import entity.Order;
import entity.OrderDetail;
import entity.OtherAddress;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountModel;
import model.CategoryModel;
import model.OrderDetailModel;
import model.OrderModel;
import model.OtherAddressModel;
import model.ProductModel;
import utils.NumberUtil;

/**
 *
 * @author Shado
 */
@WebServlet(name = "RedirectPage", urlPatterns = {"/redirectpage"})
public class RedirectPage extends HttpServlet {

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
            int page = Integer.valueOf(request.getParameter("page"));
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("currentLoginAccount");
            switch (page) {
                case 1:
                    request.setAttribute("page", 1);
                    request.getRequestDispatcher("admin-index.jsp").forward(request, response);
                    break;
                case 2:
                    ArrayList<Account> listAccount = new AccountModel().getAllAccount();
                    request.setAttribute("page", 2);
                    request.setAttribute("accounts", listAccount);
                    request.getRequestDispatcher("admin-index.jsp").forward(request, response);
                    break;
                case 3:
                    ArrayList<Product> listProduct = new ProductModel().getAllProduct();
                    ArrayList<Category> listCategory = new CategoryModel().getAllCategory();
                    request.setAttribute("page", 3);
                    request.setAttribute("products", listProduct);
                    request.setAttribute("categories", listCategory);
                    request.getRequestDispatcher("admin-index.jsp").forward(request, response);
                    break;
                case 4:
                    ArrayList<Order> listOrder = new OrderModel().getOrder();
                    request.setAttribute("page", 4);
                    request.setAttribute("listOrder", listOrder);
                    request.getRequestDispatcher("admin-index.jsp").forward(request, response);
                    break;
                default:
                    break;
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
