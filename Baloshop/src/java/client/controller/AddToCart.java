/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;

import entity.Cart;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProductModel;
import utils.NumberUtil;

/**
 *
 * @author nhatl
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/addtocart"})
public class AddToCart extends HttpServlet {

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
            String strId = request.getParameter("id");
            String strpId = request.getParameter("pid");
            int id = 0;
            boolean derect = true;

            if (strId == null) {
                id = NumberUtil.getNumber(strpId, 0);
                derect = false;
            } else {
                id = NumberUtil.getNumber(strId, 0);
            }

            Product product = new ProductModel().getOneProduct(id);
            Cart cart = new Cart(id, product.getName(), product.getPrice(), 1);

            HttpSession session = request.getSession();
            List<Cart> listOrder = null;
            listOrder = (ArrayList<Cart>) session.getAttribute("listCart");

            boolean flat = true;
            if (listOrder == null) {
                listOrder = new ArrayList<>();
                listOrder.add(cart);
                session.setAttribute("listCart", listOrder);
            } else {
                for (Cart c : listOrder) {
                    if (c.getProductId() == id) {
                        c.setQuantity(c.getQuantity() + 1);
                        flat = false;
                    }
                }
                if (flat) {
                    listOrder.add(cart);
                    flat = true;
                }
                session.setAttribute("listCart", listOrder);
            }
            if (derect) {
                response.sendRedirect("products.jsp");
            } else {
                request.setAttribute("product", product);
                request.getRequestDispatcher("product-detail.jsp").forward(request, response);
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
