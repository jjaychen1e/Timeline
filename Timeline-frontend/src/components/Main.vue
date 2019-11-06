<template>
  <div class="container py-5">
    <!-- Page Header -->
    <Header v-on:setDialogVisible="updateDialogVisible" />
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

    <!-- AddTimelineItem -->
    <!-- 该组件需要双向绑定数据 -->
    <AddTimelineItem
      v-bind:dialogVisible="dialogVisible"
      v-on:updateDialogVisible="updateDialogVisible"
      v-on:updateNewerTimeline="updateNewerTimeline"
    />
    <!-- End -->
  </div>
</template>

<script>
import Timeline from './Timeline.vue'
import Header from './Header.vue'
import UpdateButton from './UpdateButton.vue'
import AddTimelineItem from './AddTimelineItem'
import axios from 'axios'

export default {
  name: 'Main',
  components: {
    Timeline,
    UpdateButton,
    Header,
    AddTimelineItem
  },
  data() {
    return {
      timelineItems: [],
      update: 'Update',
      getMore: 'More',
      dialogVisible: false,
      mainURL: 'http://152.136.173.30:8080/'
    }
  },
  methods: {
    updateNewerTimeline() {
      axios
        .get(this.mainURL + 'TimelineItems', {
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
        .get(this.mainURL + 'TimelineItems', {
          params: {
            type: 1,
            id: this.timelineItems[this.timelineItems.length - 1].id,
            count: 2
          }
        })
        .then(res => (this.timelineItems = this.timelineItems.concat(res.data)))
        .catch(err => console.log(err))
    },
    updateDialogVisible(value) {
      this.dialogVisible = value
    }
  },
  created() {
    axios
      .get(this.mainURL + 'InitialTimelineItems', {
        params: {
          count: 3
        }
      })
      .then(res => (this.timelineItems = res.data))
      .catch(err => console.log(err))
  }
}
</script>

<style>
.file {
  position: relative;
  display: inline-block;
  /* background: #d0eeff; */
  /* border: 1px solid #99d3f5; */
  /* border-radius: 4px; */
  /* padding: 4px 12px; */
  overflow: hidden;
  color: #1e88c7;
  text-decoration: none;
  text-indent: 0;
  /* line-height: 20px; */
  width: 100%;
}
a {
  font-size: 30px;
  font-weight: 200;
}
.file input {
  position: absolute;
  font-size: 100px;
  right: 0;
  top: 0;
  opacity: 0;
}
.file:hover {
  /* background: #aadffd;
  border-color: #78c3f3;
  color: #004974; */
  text-decoration: none;
}
</style>