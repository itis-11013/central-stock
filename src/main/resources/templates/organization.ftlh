<#import "components/macros.ftlh" as macros />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <@macros.header />
    <title>Bank</title>
</head>
<body>
<@macros.navbar activeIndex=1 />
<div>
    <table class="table table-primary table-hover">
        <tr>
            <th>#ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Country Code</th>
        </tr>
        <#list list as singleBank>
        <tr>
            <td>${singleBank.innerId}</td>
            <td>${singleBank.name}</td>
            <td>${singleBank.address}</td>
            <td>${singleBank.countryCode}</td>
        </tr>
    </#list>
    </table>

</div>
</body>
</html>