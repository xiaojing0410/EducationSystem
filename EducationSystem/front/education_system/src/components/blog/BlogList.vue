<template>
  <div id="blog_list">
    <div class="list hover_effect hover_click_tip" 
    v-for="(blog,index) in blogList.result" :key = "index"
    @click="find_blog_detail(blog,blog.id)">
      <div class="blog_left">
        <img src="@/assets/test/poster.jpeg" alt="" class="blog_img" />
      </div>
      <div class="blog_right">
        <div class="blog_title">{{blog.title}}</div>
        <div class="blog_record">
          <div class="record">
            <div class="record_list blog_publish">
              <img
                src="@/assets/common/svg/blog/publish.svg"
                alt=""
                class="record_icon"
              />
              <div class="record_title">发表于<span>{{change_time_util(blog.ctime)}}</span></div>
            </div>
            <div class="record_list blog_update">
              <img
                src="@/assets/common/svg/blog/update.svg"
                alt=""
                class="record_icon"
              />
              <div class="record_title">更新于<span>{{change_time_util(blog.ctime)}}</span></div>
            </div>
          </div>
          <div class="record">
            <div class="record_list blog_tag">
              <img
                src="@/assets/common/svg/blog/tag.svg"
                alt=""
                class="record_icon"
              />
              <div class="record_title">{{blog.type}}</div>
            </div>
            <div class="record_list blog_comment">
              <img
                src="@/assets/common/svg/blog/comment.svg"
                alt=""
                class="record_icon"
              />
              <div class="record_title">{{blog.stat.commentCnt}}</div>
            </div>
            <div class="record_list blog_pcount">
              <img
                src="@/assets/common/svg/blog/pcount.svg"
                alt=""
                class="record_icon"
              />
              <div class="record_title">{{blog.stat.viewCnt}}</div>
            </div>
            <div class="record_list blog_like">
              <img
                src="@/assets/common/svg/blog/like1.svg"
                alt=""
                class="record_icon"
              />
              <div class="record_title">{{blog.stat.likeCnt}}</div>
            </div>
            <div class="record_list blog_collect">
              <img
                src="@/assets/common/svg/blog/collect1.svg"
                alt=""
                class="record_icon"
              />
              <div class="record_title">{{blog.stat.collectCnt}}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import router from "@/router"
import {change_time_util} from '@/utils/time-utils'
//文章列表展示
const props = defineProps({
  blogList: {
    type: Object,
    default: () => { }
  }
})
//查看文章详情
const find_blog_detail=(blog,id)=>{
  router.push('/detail/' + id)
}
</script>

<style lang="scss" scoped>
#blog_list{
  .list {
    width: 100%;
    height: 250px;
    margin-bottom: 20px;
    border-radius: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: var(--glass-color);
    -webkit-backdrop-filter: blur(5px);
    backdrop-filter: blur(5px);
    .blog_left {
      height: 100%;
      width: 50%;
      .blog_img {
        height: 100%;
        width: 100%;
        border-radius: 20px;
      }
    }
    .blog_right {
      height: 50%;
      width: 50%;
      padding-left: 5%;
      .blog_title {
        height: 50%;
        width: 80%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-family: var(--main-font-family);
        font-size: var(--large-font-size);
        font-weight: var(--main-font-weight);
        color: var(--text-blog-list-color);
      }
      .blog_record {
        height: 50%;
        width: 95%;
        .record {
          height: 50%;
          width: 100%;
          display: flex;
          justify-content: center;
          align-items: center;

          .record_list {
            display: flex;
            justify-content: center;
            align-items: center;
            .record_icon {
              width: 20px;
              height: 20px;
              margin-right: 5px;
            }
            .record_title {
              height: 100%;
              // min-width: 40px;
              margin-right: 20px;
            }
          }
        }
      }
    }
  }
}
</style>