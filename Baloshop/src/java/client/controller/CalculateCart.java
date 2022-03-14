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

/**
 *
 * @author Shado
 */
@WebServlet(name = "CalculateCart", urlPatterns = {"/calculatecart"})
public class CalculateCart extends HttpServlet {

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
            int more = Integer.valueOf(request.getParameter("more"));
            int id = Integer.valueOf(request.getParameter("id"));

            HttpSession session = request.getSession();
            List<Cart> listOrder = null;
            listOrder = (ArrayList<Cart>) session.getAttribute("listCart");

            if (more == 0) {
                for (int i = 0; i < listOrder.size(); i++) {
                    if (listOrder.size() > 1) {
                        if (listOrder.get(i).getProductId() == id) {
                            listOrder.remove(i);
                        }
                    } else {
                        listOrder = null;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < listOrder.size(); i++) {
                    if (listOrder.get(i).getProductId() == id) {
                        if (more == 1) {
                            listOrder.get(i).setQuantity(listOrder.get(i).getQuantity() - 1);
                            if (listOrder.size() > 1) {
                                if (listOrder.get(i).getQuantity() == 0) {
                                    listOrder.remove(i);
                                }
                            } else {
                                if (listOrder.get(i).getQuantity() == 0) {
                                    listOrder = null;
                                    break;
                                }
                            }
                        } else {
                            Product product = new ProductModel().getOneProduct(id);
                            if (listOrder.get(i).getQuantity() < product.getQuantity()) {
                                listOrder.get(i).setQuantity(listOrder.get(i).getQuantity() + 1);
                            } else {
                                listOrder.get(i).setQuantity(listOrder.get(i).getQuantity());
                            }
                        }
                    }
                }
            }

            session.setAttribute("listCart", listOrder);
            response.sendRedirect("cart.jsp");
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
