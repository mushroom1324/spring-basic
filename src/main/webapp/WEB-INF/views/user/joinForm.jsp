<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
      <div class="form-group">
        <label for="email">Email address:</label>
        <input type="email" class="form-control" placeholder="Enter email" id="email">
      </div>
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

    </form>
    <button id="btn-save" class="btn btn-primary">Register</button>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>
