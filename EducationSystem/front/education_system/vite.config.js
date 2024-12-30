import { fileURLToPath, URL } from 'node:url'
 
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// element-plus 按需引入插件
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
 
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    // element-plus配置插件
    AutoImport({
      imports:["vue","pinia","vue-router"],
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [
        // 1. 配置elementPlus采用sass样式配色系统
        ElementPlusResolver({ importStyle: "sass" }),
      ],
    })
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  css: {
    preprocessorOptions: {
      scss: {
        // 自动导入定制化样式文件进行样式覆盖
        // additionalData: `
        //   @use "@/styles/common/color/color.scss" as *;
        //   @use "@/styles/common/text/text.scss" as *;
        // `,
      }
    }
  },
  // 本地运行配置，及反向代理配置
  server: {
    // cors: true, // 默认启用并允许任何源
    // open: true, // 在服务器启动时自动在浏览器中打开应用程序
    //反向代理配置，注意rewrite写法，开始没看文档在这里踩了坑
    proxy: {// 本地开发环境通过代理实现跨域，生产环境使用 nginx 转发
      '/api': {
        target: 'http://100.94.135.96:10100', // 通过代理接口访问实际地址。这里是实际访问的地址。vue会通过代理服务器来代理请求
        changeOrigin: true,
        // ws: true,  // 允许websocket代理
        rewrite: (path) => path.replace(/^\/api/, '') // 将api替换为空
      }
    }
  }
})