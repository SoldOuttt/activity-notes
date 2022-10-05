<#import '../macros/page_tmp.ftlh' as page_tmp>
<#import '../macros/forms_tmp.ftlh' as forms>

<@page_tmp.page>
    <p>
        <a class="btn btn-success mt-5"
           data-bs-toggle="collapse"
           href="#add" role="button"
           aria-expanded="true"
           aria-controls="collapseExample">
            Add new activity
        </a>
        <a class="btn btn-success mx-3 mt-5"
           data-bs-toggle="collapse"
           href="#delete" role="button"
           aria-expanded="true"
           aria-controls="collapseExample">
            Delete activity
        </a>
    </p>

    <div class="collapse mt-5" id="add">
        <@forms.post_form "/activity/add">
            <div class="row">
                <div class="col-2">
                    <input type="text" class="form-control" placeholder="name" name="name">
                </div>
                <div class="col-2">
                    <input type="text" class="form-control" placeholder="subject name" name="subjectName">
                </div>
                <div class="col-2">
                    <input type="number" step="0.5" class="form-control" placeholder="spent time(in hours)" name="spentTime">
                </div>
                <div class="col-2">
                    <input type="date" class="form-control" placeholder="date" name="date">
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-7" style="width: 60%">
                    <input type="text" class="form-control" placeholder="description" name="description">
                </div>
                <div class="col-2">
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </div>
        </@forms.post_form>
    </div>
    <div class="collapse mt-5" id="delete">
        <@forms.post_form "/activity/deleteById">
            <div class="row" style="width: 70%">
                <div class="col-2">
                    <input type="number" class="form-control" placeholder="id" name="id">
                </div>
                <div class="col-2">
                    <button type="submit" class="btn btn-primary">Delete</button>
                </div>
            </div>
        </@forms.post_form>
    </div>

    <table class="table table-primary table-striped mt-5"
           style="width: 85%;
           border-collapse:separate;
           border-radius: 20px">
        <thead>
        <tr>
            <th scope="col" style="border-radius: 20px"><i>ID</i></th>
            <th scope="col" style="border-radius: 20px"><i>NAME</i></th>
            <th scope="col" style="border-radius: 20px"><i>SUBJECT NAME</i></th>
            <th scope="col" style="border-radius: 20px"><i>SPENT TIME</i></th>
            <th scope="col" style="border-radius: 20px"><i>DATE</i></th>
            <th scope="col" style="border-radius: 20px; width: 40%"><i>DESCRIPTION</i></th>
        </tr>
        </thead>
        <tbody>
        <#list activities as activity>
            <tr>
                <th scope="row" style="border-radius: 20px"><i>${activity.id}</i></th>
                <th style="border-radius: 20px"><i>${activity.name}</i></th>
                <td style="border-radius: 20px"><i>${activity.subjectName}</i></td>
                <td style="border-radius: 20px"><i>${activity.spentTime} hour(s)</i></td>
                <td style="border-radius: 20px"><i>${activity.date}</i></td>
                <td style="border-radius: 20px"><i>${(activity.description)!}</i></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@page_tmp.page>