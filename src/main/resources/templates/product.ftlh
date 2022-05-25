<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank</title>
</head>
<body>
<div>
    <nav>
        <div>
            <a href="/central/admin/bank">Bank</a>
            <a href="/central/admin/organization">Organization</a>
            <a href="/central/admin/contract" >Contracts</a>
            <a href="/central/admin/product" class="here">Product</a>
        </div>
    </nav>
</div>
<div>
    <table>
        <tr>
            <th>#ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Seller</th>
            <th>Product Catalog Code</th>
        </tr>
        <#list pagedResponse.content as product>
            <tr>
                <td>${product.innerId}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.count} ${product.unit} left</td>
                <td>${product.sellerId}</td>
                <td>${product.code}</td>
            </tr>
        </#list>
    </table>
</div>

<div>
    <#if pagedResponse.currentPage gt 1>
        <button onclick="location.href='/central/admin/product?page=${pagedResponse.currentPage-1}'"> &lt; </button>
    </#if>
    <span>${pagedResponse.currentPage}</span>
    <#if pagedResponse.currentPage < pagedResponse.totalPages>
        <button onclick="location.href='/central/admin/product?page=${pagedResponse.currentPage+1}'"> &gt; </button>
    </#if>

</div>
</body>
</html>