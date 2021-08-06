<@mainLayout title="Books">

<table class="table">
	<thead>
		<tr>
			<th class="col-lg-2">Title</th>
			<th class="col-lg-2">ISBN</th>
			<th class="col-lg-2">Author</th>
			<th class="col-lg-2">Publish Date</th>
			<th class="col-lg-2"></th>
		</tr>
	</thead>
	<tbody>
		<#list books as book>
		<tr>
			<td>${book.title}</td>
			<td>${book.isbn}</td>
			<td>${book.author.firstName} ${book.author.lastName}</td>
			<td>${book.publishDate}</td>
			<td><a class="btn btn-default" href='/book/${book.id}'><i class="glyphicon glyphicon-edit"></i> Edit</a></td>
		</tr>
		</#list>
	</tbody>
</table>

</@mainLayout>
