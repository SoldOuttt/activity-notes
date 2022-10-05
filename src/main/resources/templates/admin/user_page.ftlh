<#import '../macros/page_tmp.ftlh' as page_tmp>
<#import '../macros/forms_tmp.ftlh' as forms>

<@page_tmp.page>
    <p>
        <a class="btn btn-success mt-5"
           data-bs-toggle="collapse"
           href="#add" role="button"
           aria-expanded="true"
           aria-controls="collapseExample">
            Add new user
        </a>
        <a class="btn btn-success mx-3 mt-5"
           data-bs-toggle="collapse"
           href="#delete" role="button"
           aria-expanded="true"
           aria-controls="collapseExample">
            Delete User
        </a>
    </p>
    <div class="collapse mt-5" id="add">
        <@forms.post_form "/user/add">
            <div class="row">
                <div class="col-2">
                    <input type="text" class="form-control" placeholder="username" name="username">
                </div>
                <div class="col-2">
                    <input type="text" class="form-control" placeholder="password" name="password">
                </div>
                <div class="col-2 mt-1" style="width: 14%">
                    <#list roles as role>
                        <label class="form-check-label" for="${role}"><i style="color: gray">${role}</i></label>
                        <input class="form-check-input rounded-circle"
                               id="${role}"
                               type="checkbox"
                               name="${role}">
                        &ensp;
                    </#list>
                </div>
                <div class="col-2">
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </div>
        </@forms.post_form>
    </div>
    <div class="collapse mt-5" id="delete">
        <div class="input-group">
            <@forms.post_form "/user/deleteByUsername">
                <div class="row">
                    <div class="col-8">
                        <input type="text" class="form-control" placeholder="username" name="username">
                    </div>
                    <div class="col-2">
                        <button type="submit"
                                class="btn btn-primary">
                            Delete
                        </button>
                    </div>
                </div>
            </@forms.post_form>
            <@forms.post_form "/user/deleteById">
                <div class="row ms-5">
                    <div class="col-5 ms-4">
                        <input type="number" class="form-control" placeholder="id" name="id">
                    </div>
                    <div class="col-2">
                        <button type="submit"
                                class="btn btn-primary">
                            <#--                            style="background-color: lightsteelblue">-->
                            Delete
                        </button>
                    </div>
                </div>
            </@forms.post_form>
        </div>
    </div>
    <div class="input-group">
        <@forms.get_form "/user/filterByUsername">
            <div class="row mt-5" style="width: 120%">
                <div class="col-6">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><b>@</b></div>
                        </div>
                        <input type="text"
                               class="form-control"
                               id="inlineFormInputGroup"
                               placeholder="username"
                               name="username">
                    </div>
                </div>
                <div class="col-6">
                    <button type="submit"
                            class="btn btn-secondary">
                        Search by username
                    </button>
                </div>
            </div>
        </@forms.get_form>
        <@forms.get_form "/user/filterById">
            <div class="row ms-5 mt-5">
                <div class="col-5 ms-4">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><b>&#8520;</b></div>
                        </div>
                        <input type="number"
                               class="form-control"
                               id="inlineFormInputGroup"
                               placeholder="id"
                               name="id">
                    </div>
                </div>
                <div class="col-6">
                    <button type="submit"
                            class="btn btn-secondary">
                        Search by id
                    </button>
                </div>
            </div>
        </@forms.get_form>
    </div>
    <table class="table table-primary table-striped mt-4"
           style="width: 80%;
           border-collapse:separate;
           border-radius: 20px">
        <thead>
        <tr>
            <th scope="col" style="border-radius: 20px"><i>ID</i></th>
            <th scope="col" style="border-radius: 20px"><i>USERNAME</i></th>
            <th scope="col" style="border-radius: 20px"><i>PASSWORD</i></th>
            <th scope="col" style="border-radius: 20px"><i>ACCESS RIGHTS</i></th>
            <th scope="col" style="border-radius: 20px; text-align: center"><i>STATUS</i></th>
            <th scope="col" style="border-radius: 20px; text-align: center"><i>ACTIONS</i></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <th scope="row" style="border-radius: 20px"><i>${user.id}</i></th>
                <td style="border-radius: 20px">
                    <div class="d-flex align-items-center">
                        <img src="https://i.ibb.co/T8SfJtL/photo-2022-07-12-01-24-17.jpg"
                                <#--src="https://ltdfoto.ru/images/2022/06/11/photo_2022-06-11_01-19-32.jpg"-->
                             alt=""
                             style="width: 50px; height: 50px"
                             class="rounded-circle"
                        />
                        <div class="ms-3">
                            <p class="fw-bold mb-1"><i>${user.username}</i></p>
                            <p class="text-muted mb-0">tmp@mail.com</p>
                        </div>
                    </div>
                </td>
                <td style="border-radius: 20px"><i style="font-size: 115%">${user.password}</i></td>
                <td style="text-transform: lowercase; border-radius: 20px">
                    <#list user.roles as role>
                        <i style="font-size: 115%">${role}&ensp;</i>
                    </#list>
                </td>
                <#if user.active>
                    <td style="border-radius: 20px; text-align: center">
                        <i>Active</i>
                    </td>
                <#else>
                    <td style="border-radius: 20px; text-align: center">
                        <i>Frozen</i>
                    </td>
                </#if>
                <td style="border-radius: 20px; text-align: center">
                    <a href="/"
                       class="btn btn-primary btn-sm active"
                       role="button"
                       aria-pressed="true"
                       style="font-size: 85%">
                        <i>EDIT</i>
                    </a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@page_tmp.page>