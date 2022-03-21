<%-- 
    Document   : manageProduct_detail
    Created on : Mar 16, 2022, 3:24:28 PM
    Author     : 19longdt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- The Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <form action="manageProduct" method="post" enctype="multipart/form-data" acceptcharset="UTF-8">

        <div class="modal-content">
                
           
                <div class="modal-header">
                    <h5 class="modal-title">Chi tiết sản phẩm</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                
                    <div class="modal-body">
                    <div class="products-info mt-2">
                        <table class="table mt-1 mb-1">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th> 
                                    <th scope="col">Ảnh</th>        
                                    <th scope="col">Tên sản phẩm</th>
                                    <th scope="col">Giá</th>
                                    <th scope="col">Số lượng</th>
                                    <th scope="col">Loại hàng</th>
                                    <th scope="col">Trạng thái</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="text-align: center; vertical-align: middle;">
                                        <input style="width: 20px;" type="text"  name="id" value="${productDetail.id}"/>
                                    </td>
                                    <td>
                                        <div>
                                            <img style="width: 100px; height: 100px;" src="assets/images/products/${productDetail.imageLink}" alt="None">
                                            <input type="file" class="form-control-file" name="fileUp" multiple />
                                        </div>
                                    </td>
                                    <td  style="text-align: center; vertical-align: middle;">
                                        <textarea style="width: 120px; height:80px;" type="text" name="name" >${productDetail.name}</textarea>
                                    </td>
                                    <td style="text-align: center; vertical-align: middle;">
                                        <input style="width: 80px;" type="text" name="price" value="${productDetail.price}"/>
                                    </td>
                                    <td style="text-align: center; vertical-align: middle;">
                                        <input style="width: 50px;" type="number" name="quantity" value="${productDetail.quantity}" />
                                    </td>
                                    <td style="text-align: center; vertical-align: middle;">
                                        <select style="width: 120px;" name="category">
                                        <c:forEach items="${categories}" var="i" varStatus="no">
                                            <option  value="${i.id}" ${i.id == productDetail.categoryId ? 'selected="selected"' : ''}>${i.category}</option>
                                        </c:forEach>
                                    </select>
                                    </td>
                                    <td style="text-align: center; vertical-align: middle;">
                                        <select style="width: 120px;" class="form-control" name="status">
                                            <option value="1" ${productDetail.status == 1 ? 'selected="selected"' : ''}>Còn hàng</option>
                                            <option value="2" ${productDetail.status == 2 ? 'selected="selected"' : ''}>Giảm giá</option>
                                            <option value="3" ${productDetail.status == 3 ? 'selected="selected"' : ''}>Hết hàng</option>
                                            <option value="4" ${productDetail.status == 4 ? 'selected="selected"' : ''}>Ngừng kinh doanh</option>
                                    </select>
                                    </td>   
                                </tr>
                            </tbody>
                        </table>
                    </div>
                <div class="modal-footer">
                    <c:if test="${message ne null}">
                        <div class="col-md-6">
                            <span style="color: #ed1d27">${message}</span>
                        </div>
                    </c:if>
                    <input type="submit"  value="Xác nhận">
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Đóng</button>
                </div>
        </div>
    </div>
                     </form>
</div>
