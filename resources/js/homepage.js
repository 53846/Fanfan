
 
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
  //点赞设计结束//



  

  //搜索框开始//

        // 获取搜索框、搜索按钮和相关内容显示区域的引用//
const searchInput = document.getElementById('searchInput');                   //获取具有 id 为 'searchInput' 的搜索框元素的引用。
const searchButton = document.getElementById('searchButton');                 //获取具有 id 为 'searchButton' 的搜索按钮元素的引用。
const suggestionsContainer = document.getElementById('suggestionsContainer');//获取具有 id 为 'suggestionsContainer' 的相关内容显示区域元素的引用。

// 监听搜索按钮点击事件
searchButton.addEventListener('click', () => {    //监听搜索按钮的点击事件，当点击时执行相应的回调函数。
    const keyword = searchInput.value;
    performSearch(keyword);
});

// 监听搜索框的输入事件
searchInput.addEventListener('input', () => {     //监听搜索框的输入事件，当输入内容时执行相应的回调函数。
    const keyword = searchInput.value;            //获取搜索框的输入内容。

    // 向后端发送异步请求，获取相关内容
    // 这里使用了假数据作为示例
    const suggestions = getMockSuggestions(keyword);    //调用 getMockSuggestions(keyword) 函数模拟向后端发送异步请求，获取相关内容的建议。

    if (suggestions.length === 0) {
        // 如果没有相关内容，则隐藏相关内容显示区域
        suggestionsContainer.classList.add('hide');
    } else {
        // 如果有相关内容，则显示相关内容显示区域
        suggestionsContainer.classList.remove('hide');

        // 清空显示区域
        suggestionsContainer.innerHTML = '';

        // 创建相关内容的DOM元素并添加到显示区域
        suggestions.forEach(suggestion => {                     //对于 suggestions 数组中的每个元素 suggestion，执行以下操作：
            const suggestionItem = document.createElement('li');//创建一个 <li> 元素，并将其赋给 suggestionItem 变量。
            suggestionItem.textContent = suggestion;            //将 suggestion 设置为 <li> 元素的文本内容，即显示相关内容建议。
            suggestionItem.classList.add('suggestion');         //为 <li> 元素添加 suggestion 类，以便可以对其进行样式化。

            // 点击相关内容触发搜索
            suggestionItem.addEventListener('click', () => {    //为 <li> 元素添加点击事件监听器，当点击相关内容建议时执行相应的回调函数。
                searchInput.value = suggestion;                 //将相关内容建议 suggestion 设置为搜索框的值，以便显示在搜索框中。
                performSearch(suggestion);                      //调用 performSearch(suggestion) 函数执行搜索操作，例如向后端发送搜索请求并展示搜索结果。
            });

            suggestionsContainer.appendChild(suggestionItem);   //将 <li> 元素添加到 suggestionsContainer 中，作为其子元素。
        });
    }
}); 

// 模拟获取后端数据的函数
function getMockSuggestions(keyword) {         
  // 在这里执行向后端发送请求的操作，并返回相关内容的数据
  return fetch(`/search?keyword=${encodeURIComponent(keyword)}`)
  //这行代码使用fetch函数发送GET请求到指定的URL
  //URL中包含了搜索关键字作为查询参数。encodeURIComponent(keyword)用于将关键字进行URL编码，以确保特殊字符被正确处理。
      .then(response => response.json())
      //对上一步请求返回的response对象进行处理。response.json()方法将返回一个新的Promise，该Promise解析为JSON格式的响应数据。
      .then(data => data.suggestions);
      
}

// 执行搜索的函数
function performSearch(keyword) {
  // 获取后端数据
  getMockSuggestions(keyword)               //这只是一个模拟函数，实际应用中需要替换为从后端获取数据的操作。
      .then(suggestions => {                //用于在获取后端数据成功后执行的回调函数。suggestions是从后端返回的相关内容数组
          // 处理后端返回的相关内容数据
          handleSearchResponse(suggestions);    //根据后端返回的数据，动态生成相关内容的HTML，并将其插入到相关内容区域中。//
      })
      .catch(error => {             
          console.error('获取相关内容失败:', error);
      });
}

  //搜索框结束//

  
  