<template>
  <div class="container py-5">
    <!-- Page Header -->
    <Header />
    <!-- End -->

    <!-- Button -->
    <UpdateButton v-bind:buttonName="update" v-on:updateTimeline="updateNewerTimeline" />
    <!-- End -->

    <!-- Timeline -->
    <Timeline v-bind:timelineItems="timelineItems" />
    <!-- End -->

    <!-- Button -->
    <UpdateButton v-bind:buttonName="getMore" v-on:updateTimeline="updateOlderTimeline" />
    <!-- End -->

    <!-- 上传图片 -->
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
    <!-- End -->
  </div>
</template>

<script>
import Timeline from './Timeline.vue'
import Header from './Header.vue'
import UpdateButton from './UpdateButton.vue'
import axios from 'axios'

export default {
  name: 'Main',
  components: {
    Timeline,
    UpdateButton,
    Header
  },
  data() {
    return {
      timelineItems: [],
      update: 'Update',
      getMore: 'More',
      avatar: require('../assets/logo.png')
    }
  },
  methods: {
    updateNewerTimeline() {
      axios
        .get('http://127.0.0.1:8081/TimelineItems', {
          params: {
            type: 0,
            id: this.timelineItems[0].id,
            count: -1
          }
        })
        .then(res => (this.timelineItems = res.data.concat(this.timelineItems)))
        .catch(err => console.log(err))
    },
    updateOlderTimeline() {
      axios
        .get('http://127.0.0.1:8081/TimelineItems', {
          params: {
            type: 1,
            id: this.timelineItems[this.timelineItems.length - 1].id,
            count: 2
          }
        })
        .then(res => (this.timelineItems = this.timelineItems.concat(res.data)))
        .catch(err => console.log(err))
    },
    changeImage(e) {
      // 获得用户选取的图片
      var file = e.target.files[0]

      //声明一个 FileReader
      var reader = new FileReader()
      // arrow function 会重载 this, 所以先记录一个 that.
      var that = this
      reader.readAsDataURL(file)
      reader.onload = function() {
        that.avatar = this.result
      }

      var data = new FormData()
      data.append('file', file)
      data.append('userName', 'bigoldxx')
      data.append('title','Title')
      data.append('text', 'Content')

      let config = {
        headers: { 'Content-Type': 'multipart/form-data' }
      }
      this.axios
        // .post('http://152.136.173.30:8080/picSave', data, config)
        .post('localhost:8001/uploadItem', data, config)
        .then(response => {
          console.log(response)
        })
        .catch(error => {
          console.log(error)
        })
    }
  },
  created() {
    axios
      .get('http://127.0.0.1:8081/InitialTimelineItems', {
        params: {
          count: 3
        }
      })
      .then(res => (this.timelineItems = res.data))
      .catch(err => console.log(err))
  }
}
</script>