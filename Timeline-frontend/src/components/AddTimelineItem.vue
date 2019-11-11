<template>
  <el-dialog title="New Post" :visible.sync="currentDialogVisible">
    <div>
      <el-input placeholder="UserName" v-model="userName">
        <template slot="prepend">
          <i class="el-icon-user-solid"></i>
        </template>
      </el-input>
    </div>

    <div style="margin-top: 15px;">
      <el-input placeholder="Please input a title" v-model="title">
        <template slot="prepend">Title</template>
      </el-input>
    </div>

    <div style="margin-top: 15px;">
      <el-input type="textarea" :rows="5" placeholder="请输入内容" v-model="text"></el-input>
    </div>

    <div style="margin-top: 15px">
      <div align="center">
        <div taex="0" class="el-upload el-upload--picture-card">
          <img :src="avatar" v-show="avatarShow" class="img-avatar" :style="style" />
          <a v-show="!avatarShow" href="javascript:;" class="file">
            +
            <input
              type="file"
              accept="image/gif, image/jpeg, image/jpg, image/png"
              @change="chooseImage($event)"
            />
          </a>
        </div>
      </div>
      <div align="center" style="margin-top: 7px">
        <el-button @click="removeImage" size="mini" type="danger" icon="el-icon-delete" circle></el-button>
      </div>
    </div>

    <div align="right">
      <el-button
        @click="submit"
        type="primary"
        icon="el-icon-position"
        style="margin-top: 15px;"
      >POST</el-button>
    </div>
  </el-dialog>
</template>

<script>
import axios from 'axios'

export default {
  name: 'AddTimelineItem',
  props: ['dialogVisible'],
  data() {
    return {
      currentDialogVisible: this.dialogVisible,
      postUrl: 'http://152.136.173.30:8080/uploadItem',
      avatar: require('../assets/logo.png'),
      avatarShow: false,
      userName: '',
      title: '',
      text: '',
      file: '',
      style: 'margin: auto; width: 100%; height: auto'
    }
  },
  watch: {
    dialogVisible: function(newValue, oldValue) {
      oldValue
      this.currentDialogVisible = newValue
    },
    currentDialogVisible: function(newValue, oldValue) {
      oldValue
      // 只能通过观察 currentDialogVisible 来判断是否点击 X
      this.removeImage()
      this.clearInput()
      this.$emit('updateDialogVisible', newValue)
    },
    avatar: function(newValue, oldValue) {
      oldValue
      if (newValue.width >= newValue.height) {
        this.style = 'margin: auto; width: 100%; height: auto'
      } else {
        this.style = 'margin: auto; width: auto; height: 100%'
      }
    }
  },
  methods: {
    chooseImage(e) {
      // 获得用户选取的图片
      var file = e.target.files[0]
      this.file = file

      //声明一个 FileReader
      var reader = new FileReader()

      // arrow function 会重载 this, 所以先记录一个 that.
      var that = this
      reader.readAsDataURL(file)
      reader.onload = function() {
        that.avatar = this.result
        that.avatarShow = true
      }

      // 解决选择相同文件不触发 change 事件，因为我们的显示图片预览是操作 img 而不是操作 input 本身
      e.target.value = null
    },
    removeImage() {
      this.file = ''
      this.avatarShow = false
    },
    clearInput() {
      this.userName = ''
      this.title = ''
      this.text = ''
    },
    submit() {
      if (this.text == '' || this.title == '' || this.userName == '') {
        alert('UserName, Title and Content should not be null!')
        return
      }
      var data = new FormData()

      data.append('file', this.file)
      data.append('userName', this.userName)
      data.append('title', this.title)
      data.append('text', this.text)

      let config = {
        headers: { 'Content-Type': 'multipart/form-data' }
      }
      var that = this

      axios
        .post(this.postUrl, data, config)
        .then(response => {
          console.log(response.data)
          if (response.data == 1) {
            // 上传成功后自动刷新
            that.$emit('updateNewerTimeline')
            alert('上传成功')
            this.clearInput()
            this.removeImage()
            this.currentDialogVisible = false
          } else {
            alert('上传失败')
          }
        })
        .catch(error => {
          console.log(error)
          alert('上传失败')
        })
    }
  }
}
</script>