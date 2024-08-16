<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="page-content-wrapper">
    <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-2 px-4">
        <div class="d-flex align-items-center">
            <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle"></i>
            <h2 class="fs-4 m-0">Product List</h2>
        </div>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>

    <div class="container-fluid px-4">
        <div class="row mb-3">
            <div class="col d-flex justify-content-end">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addProductModal">
                    Add New Product
                </button>
            </div>
        </div>

        <div class="dropdown mb-3">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Category
            </a>
            <ul class="dropdown-menu" style="border-color: black">
                <c:forEach items="${listCategory}" var="category">
                    <li><a class="dropdown-item" href="./listProduct?id_category=${category.id}">${category.name}</a></li>
                </c:forEach>
            </ul>
        </div>

        <div class="row">
            <div class="col">
                <table class="table bg-white rounded shadow-sm table-hover mb-0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${listProduct}">
                            <c:choose>
                                <c:when test="${id_category == null}">
                                    <tr>
                                        <td>${product.id}</td>
                                        <td>${product.name}</td>
                                        <td><img src="./assets/images/${product.image}" alt="${product.name}" width="50"></td>
                                        <td>${product.price}</td>
                                        <td>${product.quantity}</td>
                                        <td>${product.status}</td>
                                        <td>
                                            <!-- Nút "S?a" -->
                                            <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editProductModal"
                                                    onclick="
                                                        document.getElementById('editProductId').value = '${product.id}';
                                                        document.getElementById('editProductName').value = '${product.name}';
                                                        document.getElementById('editProductPrice').value = '${product.price}';
                                                        document.getElementById('editProductQuantity').value = '${product.quantity}';
                                                        document.getElementById('editProductStatus').value = '${product.status}';
                                                        document.getElementById('editProductImage').value = '${product.image}';
                                                    ">
                                                <img src="./assets/icon/update.png" width="30">
                                            </button>
                                            <!-- Nút "Xóa" -->
                                             <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmationModal"
                                                    onclick="
                                                        document.getElementById('deleteProductName').textContent = '${product.name}';
                                                        document.getElementById('deleteProductPrice').textContent = '${product.price}';
                                                        document.getElementById('deleteProductQuantity').textContent = '${product.quantity}';
                                                        document.getElementById('deleteProductImage').src = './assets/images/${product.image}';
                                                        document.getElementById('confirmDeleteBtn').href = 'deleteProduct?id_product=${product.id}';
                                                    ">
                                                <img src="./assets/icon/delete.png" width="30">
                                            </button>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${id_category == product.id_category}">
                                        <tr>
                                            <td>${product.id}</td>
                                            <td>${product.name}</td>
                                            <td><img src="./assets/images/${product.image}" alt="${product.name}" width="50"></td>
                                            <td>${product.price}</td>
                                            <td>${product.quantity}</td>
                                            <td>${product.status}</td>
                                            <td>
                                                <!-- Nút "S?a" -->
                                                <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editProductModal"
                                                        onclick="
                                                            document.getElementById('editProductId').value = '${product.id}';
                                                            document.getElementById('editProductName').value = '${product.name}';
                                                            document.getElementById('editProductPrice').value = '${product.price}';
                                                            document.getElementById('editProductQuantity').value = '${product.quantity}';
                                                            document.getElementById('editProductStatus').value = '${product.status}';
                                                            document.getElementById('editProductImage').value = '${product.image}';
                                                        ">
                                                    <img src="./assets/icon/update.png" width="30">
                                                </button>
                                                <!-- Nút "Xóa" -->
                                                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmationModal"
                                                        onclick="
                                                            document.getElementById('deleteProductName').textContent = '${product.name}';
                                                            document.getElementById('deleteProductPrice').textContent = '${product.price}';
                                                            document.getElementById('deleteProductQuantity').textContent = '${product.quantity}';
                                                            document.getElementById('deleteProductImage').src = './assets/images/${product.image}';
                                                            document.getElementById('confirmDeleteBtn').href = 'deleteProduct?id_product=${product.id}';
                                                        ">
                                                    <img src="./assets/icon/delete.png" width="30">
                                                </button>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Modal for delete confirmation -->
<div class="modal fade" id="deleteConfirmationModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Delete Confirmation</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Product information to be deleted -->
                <h6>Are you sure you want to delete the following product?</h6>
                <div class="card">
                    <div class="row g-0">
                        <div class="col-md-4">
                            <img id="deleteProductImage" src="" alt="Product Image" class="img-fluid rounded-start" style="width: 100px;">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title" id="deleteProductName"></h5>
                                <p class="card-text"><strong>Price:</strong> $<span id="deleteProductPrice"></span></p>
                                <p class="card-text"><strong>Quantity:</strong> <span id="deleteProductQuantity"></span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <a id="confirmDeleteBtn" href="#" class="btn btn-danger">Delete</a>
            </div>
        </div>
    </div>
</div>

<!-- Modal for edit product -->
<div class="modal fade" id="editProductModal" tabindex="-1" aria-labelledby="editProductModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editProductModalLabel">Edit Product</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Form to edit product -->
                <form action="editProduct" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" id="editProductId">
                    <div class="mb-2">
                        <label for="editProductName" class="form-label">Name</label>
                        <input type="text" class="form-control" id="editProductName" name="name" required>
                    </div>
                    <div class="mb-2">
                        <label for="editProductPrice" class="form-label">Price</label>
                        <input type="number" class="form-control" id="editProductPrice" name="price" required>
                    </div>
                    <div class="mb-2">
                        <label for="editProductQuantity" class="form-label">Quantity</label>
                        <input type="number" class="form-control" id="editProductQuantity" name="quantity" required>
                    </div>
                    <div class="mb-3">
                        <label for="addProductCategory" class="form-label">Category</label>
                        <select class="form-control" id="addProductCategory" name="id_category" required>
                            <c:forEach var="category" items="${listCategory}">
                                <option value="${category.id}" <c:if test="${category.id == product.id_category}">selected</c:if>>${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-2">
                        <label for="editProductStatus" class="form-label">Status</label>
                        <select class="form-control" id="editProductStatus" name="status">
                            <option value="true">Available</option>
                            <option value="false">Unavailable</option>
                        </select>
                    </div>
                    <div class="mb-2">
                        <label for="editProductImage" class="form-label">Image</label>
                        <input type="file" class="form-control" id="editProductImage" name="image" accept="image/*">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- Trong t?p tin JSP ho?c HTML có ch?a modal -->
<!-- Modal for adding new product -->
<div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addProductModalLabel">Add New Product</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Form to add new product -->
                <!-- Form to add new product -->
            <form action="addProduct" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="addProductName" class="form-label">Name</label>
                    <input type="text" class="form-control" id="addProductName" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="addProductPrice" class="form-label">Price</label>
                    <input type="number" class="form-control" id="addProductPrice" name="price" required>
                </div>
                <div class="mb-3">
                    <label for="addProductQuantity" class="form-label">Quantity</label>
                    <input type="number" class="form-control" id="addProductQuantity" name="quantity" required>
                </div>
                <div class="mb-3">
                    <label for="addProductCategory" class="form-label">Category</label>
                    <select class="form-control" id="addProductCategory" name="category" required>
                        <c:forEach var="category" items="${listCategory}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="addProductStatus" class="form-label">Status</label>
                    <select class="form-control" id="addProductStatus" name="status">
                        <option value="true">Available</option>
                        <option value="false">Unavailable</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="addProductImage" class="form-label">Image</label>
                    <input type="file" class="form-control" id="addProductImage" name="image" required>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Add Product</button>
                </div>
            </form>

            </div>
        </div>
    </div>
</div>
