<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form action="/action_page.php">
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="username" class="form-control" placeholder="Enter username" id="username">
      </div>
      <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" class="form-control" placeholder="Enter password" id="pwd">
      </div>
      <div class="form-group form-check">
        <label class="form-check-label">
          <input class="form-check-input" type="checkbox"> Remember me
        </label>
      </div>
      <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div>

<%@ include file="../layout/footer.jsp" %>