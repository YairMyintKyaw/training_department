<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Training Department | Home</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css
    "
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
    />
    <link rel="stylesheet" href="css/style.css" />
  </head>
  <body class="text-bg-dark">
    <!-- nav -->
    <jsp:include page="header.jsp"></jsp:include>

    <!-- hero section -->
    <section class="container">
      <div
        class="row align-items-center overflow-hidden flex-wrap-reverse gap-4 p-4 gap-lg-0 p-lg-0"
        style="min-height: 80vh"
      >
        <div
          class="col-8 text-center text-lg-start m-auto m-lg-0 col-lg-5 animate__animated animate__fadeInLeft leftSide"
        >
          <div class="d-flex flex-column gap-4">
            <h1 class="fs-1 fw-bold"> <span class="typed">Training Department</span></h1>
            <p
              class="fs-6"
              style="text-align: justify; text-justify: inter-word"
            >
              Training Department offers variety of courses. Learner can be able
              to choose the time that matches their needs. For each course, the
              user can choose Morning Session or Evening Session. It i€™s your
              choice.Training Department will improve you to next level.
            </p>
            <div>
              <a href="<%=request.getContextPath()%>/new" class="btn btn-outline-light rounded-2 w-50 p-2 fs-5"
                >Register</a
              >
            </div>
          </div>
        </div>
        <div class="col-12 col-lg-1"></div>
        <div class="col-12 col-lg-6 animate__animated animate__fadeInRight">
          <img src="./images/PngItem_1620792.png" class="img-fluid" alt="" />
        </div>
      </div>
    </section>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js
    "></script>
    <script src="https://cdn.jsdelivr.net/npm/typed.js@2.0.12"></script>
    <script src="index.js"></script>
    <script type="text/javascript">
    
    /* document.querySelector(".leftSide").addEventListener("animationend",function(){
    	new Typed('.typed', {
            strings: ["Training Department"],
            typeSpeed: 30
          });
    }) */
    </script>
  </body>
</html>
