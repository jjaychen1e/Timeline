<template>
  <div class="test">
    <div class="container py-5">
      <!-- For demo purpose -->
      <div class="row text-center text-white mb-5">
        <div class="col-lg-8 mx-auto">
          <h1 class="display-4">Bootstrap timeline</h1>
          <p class="lead mb-0">Build a responsive vertical timeline using Bootstrap 4.</p>
          <p class="lead">
            Snippet by
            <a href="https://bootstrapious.com/snippets" class="text-white">
              <u>Bootstrapious</u>
            </a>
          </p>
        </div>
      </div>
      <!-- End -->

      <div class="row">
        <div class="col-lg-7 mx-auto">
          <!-- Timeline -->
          <ul class="timeline">
            <li class="timeline-item bg-white rounded ml-3 p-4 shadow">
              <div class="timeline-arrow"></div>
              <h2 class="h5 mb-0">Title of section 1</h2>
              <span class="small text-gray">
                <i class="fa fa-clock-o mr-1"></i>21 March, 2019
              </span>
              <p class="text-small mt-2 font-weight-light">
                Lorem ipsum dolor sit amet, consectetur adipiscing
                elit. Quisque scelerisque diam non nisi semper, et elementum lorem ornare. Maecenas placerat
                facilisis mollis. Duis sagittis ligula in sodales vehicula....
              </p>
            </li>
            <li class="timeline-item bg-white rounded ml-3 p-4 shadow">
              <div class="timeline-arrow"></div>
              <h2 class="h5 mb-0">Title of section 2</h2>
              <span class="small text-gray">
                <i class="fa fa-clock-o mr-1"></i>5 April, 2019
              </span>
              <p class="text-small mt-2 font-weight-light">
                Lorem ipsum dolor sit amet, consectetur adipiscing
                elit. Quisque scelerisque diam non nisi semper.
              </p>
              <p class="text-small mt-2 font-weight-light">
                Libero expedita explicabo eius fugiat quia aspernatur
                autem laudantium error architecto recusandae natus sapiente sit nam eaque, consectetur porro
                molestiae ipsam! Deleniti.
              </p>
            </li>
            <li class="timeline-item bg-white rounded ml-3 p-4 shadow">
              <div class="timeline-arrow"></div>
              <h2 class="h5 mb-0">Title of section 3</h2>
              <span class="small text-gray">
                <i class="fa fa-clock-o mr-1"></i>18 August, 2019
              </span>
              <p class="text-small mt-2 font-weight-light">
                Lorem ipsum dolor sit amet, consectetur adipiscing
                elit. Quisque scelerisque diam non nisi semper, et elementum lorem ornare. Maecenas placerat
                facilisis mollis. Duis sagittis ligula in sodales vehicula....
              </p>
            </li>
            <li class="timeline-item bg-white rounded ml-3 p-4 shadow">
              <div class="timeline-arrow"></div>
              <h2 class="h5 mb-0">Title of section 4</h2>
              <span class="small text-gray">
                <i class="fa fa-clock-o mr-1"></i>10 October, 2019
              </span>
              <p class="text-small mt-2 font-weight-light">
                Lorem ipsum dolor sit amet, consectetur adipiscing
                elit. Quisque scelerisque diam non nisi semper, et elementum lorem ornare. Maecenas placerat
                facilisis mollis. Duis sagittis ligula in sodales vehicula....
              </p>
              <p class="text-small mt-2 font-weight-light">
                Voluptatibus temporibus esse illum eum aspernatur,
                fugiat suscipit natus! Eum corporis illum nihil officiis tempore. Excepturi illo natus libero
                sit doloremque, laborum molestias rerum pariatur quam ipsam necessitatibus incidunt,
                explicabo.
              </p>
            </li>
            <img :src="avatar" class="img-avatar" />
            <input
              type="file"
              name="avatar"
              id="uppic"
              accept="image/gif, image/jpeg, image/jpg, image/png"
              @change="changeImage($event)"
              ref="avatarInput"
              class="uppic"
            />
          </ul>
          <!-- End -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'test',
  data() {
    return {
      avatar: require('../assets/ex.png')
    }
  },
  methods: {
    changeImage(e) {
      var file = e.target.files[0]
      var reader = new FileReader()
      var that = this
      reader.readAsDataURL(file)
      reader.onload = function() {
        that.avatar = this.result
      }
      var data = new FormData()
      data.append('file', file)
      let config = {
        headers: { 'Content-Type': 'multipart/form-data' }
      }
      this.$axios
        .post('http://152.136.173.30:8080/picSave', data, config)
        .then(response => {
          console.log(response)
        })
        .catch(error => {
          console.log(error)
        })
    }
  }
}
</script>

<style>
</style>