<#import '../macros/page_tmp.ftlh' as page_tmp>
<#import '../macros/forms_tmp.ftlh' as forms>

<@page_tmp.page>
    <p>
        <a class="btn btn-success mt-5"
           data-bs-toggle="collapse"
           href="#add" role="button"
           aria-expanded="true"
           aria-controls="collapseExample">
            Add new subject
        </a>
        <a class="btn btn-success mx-3 mt-5"
           data-bs-toggle="collapse"
           href="#delete" role="button"
           aria-expanded="true"
           aria-controls="collapseExample">
            Delete subject
        </a>
    </p>
    <div class="collapse mt-5" id="add">
        <@forms.post_form "/subject/add">
            <div class="row">
                <div class="col-2">
                    <input type="text" class="form-control" placeholder="name" name="name">
                </div>
                <div class="col-2">
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </div>
        </@forms.post_form>
    </div>
    <div class="collapse mt-5" id="delete">
        <@forms.post_form "/subject/deleteByName">
            <div class="row">
                <div class="col-2">
                    <input type="text" class="form-control" placeholder="name" name="name">
                </div>
                <div class="col-2">
                    <button type="submit" class="btn btn-primary">Delete</button>
                </div>
            </div>
        </@forms.post_form>
    </div>


    <table class="table table-primary table-striped mt-5"
           style="width: 30%;
           border-collapse:separate;
           border-radius: 20px">
        <thead>
        <tr>
            <th scope="col" style="border-radius: 20px"><i style="line-height: 300%">&numero;</i></th>
            <th scope="col" style="border-radius: 20px"><i style="line-height: 300%">MY SUBJECTS</i></th>
            <th scope="col" style="border-radius: 20px; width: 44%"><i>TOTAL TIME SPENT (IN HOURS)</i></th>
        </tr>
        </thead>
        <tbody>
        <#list subjects as subject>
            <tr>
                <th scope="row" style="border-radius: 20px"><i>${subject_index + 1}</i></th>
                <th style="border-radius: 20px"><i>${subject.name}</i></th>
                <td style="border-radius: 20px"><i>${subject.totalTime}</i></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@page_tmp.page>