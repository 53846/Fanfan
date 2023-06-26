  //首页翻页
layui.use(function(){
    var laypage = layui.laypage;
    // 完整显示
    laypage.render({
      elem: 'demo-laypage-all', // 元素 id
      count: 100, // 数据总数
      layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'], // 功能布局
      jump: function(obj){
        console.log(obj);
      }
    });
  });
    //首页左方
layui.use(['element', 'layer', 'util'], function(){
    var element = layui.element;
    var layer = layui.layer;
    var util = layui.util;
    var $ = layui.$;
    //头部事件
    util.event('lay-header-event', {
      menuLeft: function(othis){ // 左侧菜单事件
        layer.msg('展开左侧菜单的操作', {icon: 0});
      },
    });
  });
  //翻页  
  function changePage(pageNum) {
    // 隐藏所有页面
    var pages = document.querySelectorAll('.page');
    for (var i = 0; i < pages.length; i++) {
      pages[i].style.display = 'none';
    }
  
    // 显示目标页面
    var targetPage = document.querySelector('#page' + pageNum);
    targetPage.style.display = 'block';
  }

  
// 假设有一个包含所有图片路径的数组
var imageArray = [/* 图片路径数组 */];
var pageSize = 5; // 每页显示的图片数量
var currentPage = 1; // 当前页码

// 获取图片容器和按钮容器的引用
var imageContainer = document.getElementById('imageContainer');
var paginationContainer = document.getElementById('paginationContainer');

// 初始化页面
renderPage(currentPage);

// 渲染当前页的图片和按钮
function renderPage(page) {
  // 清空图片容器中的内容
  imageContainer.innerHTML = '';
  
  // 计算当前页需要显示的图片的起始索引和结束索引
  var startIndex = (page - 1) * pageSize;
  var endIndex = Math.min(startIndex + pageSize, imageArray.length);
  
  // 遍历当前页的图片并将其添加到图片容器中
  for (var i = startIndex; i < endIndex; i++) {
    var imageSrc = imageArray[i];
    
    // 创建图片元素并设置图片路径
    var img = document.createElement('img');
    img.src = imageSrc;
    
    // 将图片元素添加到图片容器中
    imageContainer.appendChild(img);
  }
  
  // 渲染分页按钮
  renderPagination(page);
}

// 渲染分页按钮
function renderPagination(currentPage) {
  // 清空按钮容器中的内容
  paginationContainer.innerHTML = '';
  
  // 计算总页数
  var pageCount = Math.ceil(imageArray.length / pageSize);
  
  // 创建按钮并添加到按钮容器中
  for (var i = 1; i <= pageCount; i++) {
    var button = document.createElement('button');
    button.textContent = i;
    button.value = i;
    
    // 设置当前页按钮的样式
    if (i === currentPage) {
      button.classList.add('active');
    }
    
    // 绑定按钮的点击事件
    button.addEventListener('click', function() {
      var page = parseInt(this.value);
      currentPage = page;
      renderPage(currentPage);
    });
    
    // 将按钮添加到按钮容器中
    paginationContainer.appendChild(button);
  }
}
