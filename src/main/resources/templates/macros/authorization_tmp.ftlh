<#import 'tokens.ftlh' as tokens>

<#macro login>
    <form method="post" action="/login">
        <div>
            <label> username:
                <input type="text" name="username"/>
            </label>
        </div>
        <br/>
        <div>
            <label> password:
                <input type="password" name="password"/>
            </label>
        </div>
        <br/>
        <div>
            <input type="submit" value="sign in"/>
        </div>
        <@tokens.csrf_token/>
    </form>
</#macro>

<#macro register message>
    <form method="post" action="/register">
        <div>
            <label> username :
                <input type="text" name="username"/>
            </label>
            <i style="color: crimson">&nbsp;${message!}</i>
        </div>
        <br/>
        <div>
            <label> password:
                <input type="password" name="password"/>
            </label>
        </div>
        <br/>
        <div>
            <input type="submit" value="sign up"/>
        </div>
        <@tokens.csrf_token/>
    </form>
</#macro>

<#macro logout>
    <div>
        <form method="post" action="/logout">
            <input type="submit" value="sign out"/>
            <@tokens.csrf_token/>
        </form>
    </div>
</#macro>