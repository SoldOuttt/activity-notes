<#import 'tokens.ftlh' as tokens>

<#macro post_form path>
    <div>
        <form method="post" action=${path}>
            <#nested>
            <@tokens.csrf_token/>
        </form>
    </div>
</#macro>

<#macro get_form path>
    <div>
        <form method="get" action=${path}>
            <#nested>
        </form>
    </div>
</#macro>