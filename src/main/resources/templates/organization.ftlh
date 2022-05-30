<#import "components/macros.ftlh" as macros />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <@macros.header />
    <title>Organization</title>
</head>
<body>
<@macros.navbar activeIndex=1 />
<h2>Organizations</h2>
<label for="country-select">Select a country to filter: </label>
<script>
    selectHandler = {
        clickCount : 0,
        action : function(select)
        {
            selectHandler.clickCount++;
            let selectedValue;
            if (selectHandler.clickCount % 2 === 0) {
                selectedValue = select.options[select.selectedIndex].value;
                selectHandler.check(selectedValue);
            }
        },
        blur : function() // needed for proper behaviour
        {
            if(selectHandler.clickCount%2 !== 0)
            {
                selectHandler.clickCount--;
            }
        },
        check : function(value)
        {
            console.log('check here and value is ', value)
            if (value != null) getOrganizationFrom(value);
        }
    }

</script>
<select class="form-select" id="country-select" onclick="selectHandler.action(this)">
    <option value="" <#if country?? == false>selected</#if>>---</option>
    <#list countries as count>
        <option value="${count}" <#if (country!'') == count>selected</#if>>
            ${count?upper_case}
        </option>
    </#list>
</select>
<div class="table-responsive">
    <table class="table table-primary table-hover" id="OrganizationTable">
        <tr>
            <th></th>
            <th>#ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Country Code</th>
        </tr>
        <#list list as singleBank>
            <tr>
                <td onclick="getProducts(this, [<#list singleBank.products as prod>'${prod}',</#list>])">
                    <button class="btn btn-primary">&downarrow;</button>
                </td>
                <td>${singleBank.innerId}</td>
                <td>${singleBank.name}</td>
                <td>${singleBank.address}</td>
                <td>${singleBank.countryCode}</td>
            </tr>
            <tr class="hidden-details">
                <td colspan="5">
                    <h3>Products</h3>
                    <table class="table table-striped table-info w-75">
                    </table>
                </td>
            </tr>
        </#list>
    </table>

</div>
</body>
</html>