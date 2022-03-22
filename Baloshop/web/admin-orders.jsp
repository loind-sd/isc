<%-- 
    Document   : admin-orders
    Created on : Oct 21, 2018, 11:36:18 PM
    Author     : Shado
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="right-side mb-5">
    <div class="row title">
        <div class="col-md-12">
            <h6>
                Quản lý đơn hàng
            </h6>
        </div>
    </div>

    <div class="row mt-2">
        <div class="col-md-12">
            <table id="orders" table class="table table-bordered" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Mã hóa đơn</th>
                        <th>Người nhận</th>
                        <th>Số điện thoại</th>
                        <th>Địa chỉ người nhận</th>
                        <th>Giá trị</th>
                        <th>Trạng thái</th>
                        <th>Chức năng</th>
                        <th>Chi tiết</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listOrder}" var="i" varStatus="no">
                        <tr>
                            <td>${no.index+1}</td>
                            <td>${i.id}</td>
                            <td>${i.name}</td>
                            <td>${i.phone}</td>
                            <td>${i.address}</td>
                            <td><fmt:formatNumber type="number" maxFractionDigits="3" value="${i.totalPrice}"/><sup>đ</sup></td>


                    <c:if test="${currentLoginAccount.roleId eq 1}">
                        <c:if test="${i.status == 1}">
                            <td class="text-center">
                                <span class="active">Chờ xác nhận</span>
                            </td>
                            <td>
                                <a class="nav-link" href="confirmOrder?id=${i.id}">Xác nhận</a>
                            </td>
                        </c:if> 
                        <c:if test="${i.status != 1}">
                            <td class="text-center">
                                <span class="active">Đã xác nhận</span>
                            </td>
                            <td></td>
                        </c:if> 
                    </c:if>

                    <c:if test="${currentLoginAccount.roleId eq 4}">
                        <td class="text-center">
                            <c:if test="${i.status == 2}">
                                <span class="active">Đang chuẩn bị hàng</span>
                            </c:if>

                            <c:if test="${i.status == 3}">
                                <span class="active">Đang giao hàng</span>
                            </c:if>

                            <c:if test="${i.status == 4}">
                                <span class="active">Đã giao hàng</span>
                            </td>
                            <td>
                            </c:if>

                            <c:if test="${i.status == 5}">
                                <span class="active">Đang hoàn trả hàng</span>
                            </c:if>

                            <c:if test="${i.status == 6}">
                                <span class="active">Đã hoàn trả hàng</span>
                            </td>
                            <td>
                            </c:if>

                            <c:if test="${i.status == 7}">
                                <span class="deactiveactive">Đã hủy đơn hàng</span>  
                            </td>
                            <td>
                            </c:if> 
                        </td>

                        <c:if test="${i.status != 7}">
                            <c:if test="${i.status != 6}">
                                <c:if test="${i.status != 4}">
                                    <td>
                                        <a class="nav-link" href="confirmOrder?id=${i.id}&status=${i.status + 1}">Xong</a>
                                    </td>
                                </c:if>
                            </c:if>
                        </c:if>
                    </c:if>

                    <td><a href="orders?id=${i.id}&flag=1" class="g-color">Chi tiết</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

