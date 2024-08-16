<div class="add-product-container">
        <span style="color: red; margin-left: 40px; font-size: 21px">${exist_product}</span>
        <h2>Add Product</h2>
        <form class="add-product-form" action="" method="post" enctype="multipart/form-data">
            <div>
                <label for="name">Name:</label>
                <input type="text"  name="name" required>
    -
            </div>
            <div>
                <label for="image">Image:</label>
                <input type="text"  name="image" required>
            </div>
            <div>
                <label for="price">Price:</label>
                <input type="number"  name="price" step="0.01" required>
            </div>
            <div>
                <label for="quantity">Quantity:</label>
                <input type="number"  name="quantity" required>
            </div>
            <div>
                <label for="status">Status:</label>
                <select type="text" name="status" required>
                    <option value="0">Inactive</option>
                    <option value="1">Active</option>
                </select>
            </div>
            <div>
                <label for="id_category">Category:</label>
                <select  type="text" name="id_category" required>
                    <option value="1">Smart Phone</option>
                    <option value="2">Laptop</option>
                    <option value="3">Smart TV</option>
                    <option value="4">Watch</option>
                    <!-- Add more categories as needed -->
                </select>
            </div>
            <div>
                <button type="submit"data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg">Add Product</button>
            </div>
        </form>
    </div>