<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light mb-4 p-3 shadow-sm rounded">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="#" style="font-size: 1.5rem;">Categories:</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCategories" aria-controls="navbarCategories" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCategories">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <c:forEach items="${listCategory}" var="category">
                    <li class="nav-item">
                        <a class="nav-link text-primary fw-bold px-3" href="home?id_category=${category.id}" style="font-size: 1.2rem; transition: color 0.3s;">
                            ${category.name}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>

<!-- Danh sách s?n ph?m theo category -->
<c:if test="${id_category == null}"> 
    <c:forEach items="${listCategory}" var="category">    
        <section>
            <div class="container my-5">
                <header class="mb-4">
                    <h3>${category.name}</h3>
                </header>
                <div class="row">
                    <c:forEach items="${listProduct}" var="product">
                        <c:if test="${category.id == product.id_category}">
                            <div class="col-lg-3 col-md-6 col-sm-6 d-flex">
                                <div class="card w-100 my-2 shadow-2-strong">
                                    <img src="./assets/images/${product.image}" class="card-img-top" style="aspect-ratio: 1 / 1" />
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">${product.name}</h5>
                                        <p class="card-text">${product.price}</p>
                                        <div class="card-footer d-flex align-items-end pt-3 px-0 pb-0 mt-auto">
                                            <a href="
                                               <c:if test="${user==null}">
                           login
                       </c:if>
                       <c:if test="${user!=null}">
                           home?id_product=${product.id}
                       </c:if>  
                                               " class="btn btn-primary shadow-0 me-1">Add to cart</a>
                                            <a href="#!" class="btn btn-light border px-2 pt-2 icon-hover"><i class="fas fa-heart fa-lg text-secondary px-1">  <img src="./assets/icon/heart.png" alt="" width="25" height="25"/>  </i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </section>
    </c:forEach>
</c:if>

<c:if test="${id_category != null}"> 
    <c:forEach items="${listCategory}" var="category">    
        <c:if test="${category.id == id_category}">  
            <section>
                <div class="container my-5">
                    <header class="mb-4">
                        <h3>${category.name}</h3>
                    </header>
                    <div class="row">
                        <c:forEach items="${listProduct}" var="product">
                            <c:if test="${category.id == product.id_category}">
                                <div class="col-lg-3 col-md-6 col-sm-6 d-flex">
                                    <div class="card w-100 my-2 shadow-2-strong">
                                        <img src="./assets/images/${product.image}" class="card-img-top" style="aspect-ratio: 1 / 1" />
                                        <div class="card-body d-flex flex-column">
                                            <h5 class="card-title">${product.name}</h5>
                                            <p class="card-text">${product.price}</p>
                                            <div class="card-footer d-flex align-items-end pt-3 px-0 pb-0 mt-auto">
                                                <a href="
                                                <c:if test="${user==null}">
                           login
                       </c:if>
                       <c:if test="${user!=null}">
                           home?id_product=${product.id}&id_category=${category.id}
                       </c:if> 
                                                   " class="btn btn-primary shadow-0 me-1">Add to cart</a>
                                                <a href="#!" class="btn btn-light border px-2 pt-2 icon-hover"><i class="fas fa-heart fa-lg text-secondary px-1">  <img src="./assets/icon/heart.png" alt="" width="25" height="25"/>  </i></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </section>
        </c:if>
    </c:forEach>
</c:if>
