
 
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

  
  layui.use(['laypage'], function() {
    var laypage = layui.laypage;

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
  });

    //点赞设计开始
    var isLiked = false;
    var likeButton = document.getElementById('likeButton');
    var likeCount = document.getElementById('likeCount');
    var lkcount = 0;      //改为实际的点赞数
    
    function toggleLike() {
      if (isLiked) {
        unlike();
      } else {
        like();
      }
    }
    
    function like() {
      isLiked = true;
      likeButton.innerHTML = '已赞';
      likeButton.classList.add('liked');
      lkcount++;
      updateLikeCount();
    }
    
    function unlike() {
      isLiked = false;
      likeButton.innerHTML = '点赞';
      likeButton.classList.remove('liked');
      lkcount--;
      updateLikeCount();
    }
    
    function updateLikeCount() {
      likeCount.innerHTML = lkcount;
    }
  //点赞设计结束

