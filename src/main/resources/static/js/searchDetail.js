let app = new Vue({
    el: '#app',
    data: {
        urls: {
            getMoviesByName: 'http://localhost:8666/api/movie/getMoviesByName',
            getRecommendMovies: 'http://localhost:8666/api/movie/getRecommendMovies',
            getRelatedMovies: 'http://localhost:8666/api/movie/getRelatedMovies',
            getRecentTopMovies: 'http://localhost:8666/api/movie/getRecentTopMovies'
            //todo wh:打分URL
        },
        page: {
            userName: '',
            show: false
        },
        table: {
            searchResult: [],
            type: '',
            orderBy: 'averating',
            sortBy: 'desc',
            props: {
                searchKey: '',
                pageIndex: 1,
                pageSize: 12,
                total: 0
            }
        },
        dialog: {
            show: false,
            movie: {},
            rating: 0,
            relatedMovies: []
        },
        recommendMovies: [],
        topMovies: []
    },
    methods: {
        clickHeadIcon: function () {
            window.open("./homepage.html", "_self")
        },
        handleImgClick: function (item) {
            window.open("./searchDetail.html?" + item.movieName, "_self")
        },
        search: function () {
            let app = this;
            window.open("./searchDetail.html?" + app.table.props.searchKey)
        },
        logout: function () {
            delSessionStorage('userName');
            this.$message({
                message: "您已成功退出",
                type: 'success'
            });
            setTimeout(function () {
                window.open("./welcome.html", "_self")
            }, 2000);
        },
        onPageIndexChange: function (newIndex) {
            this.table.props.pageIndex = newIndex;
            this.getSearchResult();
        },
        getSearchResult: function () {
            let app = this;
            this.table.searchResult = [];
            let data = {
                page: app.table.props,
                movieName: app.table.props.searchKey,
                types: app.table.type,
                orderBy: app.table.orderBy,
                sortBy: app.table.sortBy,
            };
            ajaxPostJSON(
                this.urls.getMoviesByName,
                data,
                function (result) {
                    if (result.code === 'success') {
                        app.table.props.total = result.data.total;
                        result.data.resultList.forEach(function (r) {
                            app.table.searchResult.push(r)
                        })
                    } else
                        app.$message({
                            message: '服务器错误，原因\n' + result.data,
                            type: 'error'
                        });
                },
                function () {
                    app.$message({
                        message: '获取查询结果失败',
                        type: 'error'
                    });
                }
            )
        },
        getRelatedMovies: function (movie) {
            let app = this;
            //获取相似电影推荐
            ajaxPostJSON(
                this.urls.getRelatedMovies,
                {id: movie.id},
                function (result) {
                    if (result.code === 'success') {
                        result.data.resultList.forEach(function (r) {
                            app.dialog.relatedMovies.push(r)
                        })
                    } else
                        app.$message({
                            message: '服务器错误，原因\n' + result.data,
                            type: 'error'
                        });
                },
                function () {
                    app.$message({
                        message: '获取相似电影推荐失败',
                        type: 'error'
                    });
                }
            )
        },
        openDetailDialog: function (movie) {
            this.dialog.movie = movie;
            this.getRelatedMovies(movie);
            this.dialog.show = true;

        },
        closeDetailDialog: function () {
            this.dialog = {
                show: false,
                movie: {},
                rating: 0,
                relatedMovies: []
            }
        },
        onRating: function () {
            console.log("打分")
            //todo wh:打分
        }
    },
    created: function () {
        let userName = getSessionStorage('userName');
        if (userName !== null) {
            let app = this;
            this.page.userName = userName;
            this.table.props.searchKey = decodeURI(window.location).split('?')[1];
            this.getSearchResult();

            //获取个性化推荐结果
            ajaxPost(
                app.urls.getRecommendMovies,
                {userName: userName},
                function (result) {
                    if (result.code === 'success') {
                        result.data.resultList.forEach(function (r) {
                            app.recommendMovies.push(r)
                        })
                    } else
                        app.$message({
                            message: '服务器错误，原因\n' + result.data,
                            type: 'error'
                        });
                },
                function () {
                    app.$message({
                        message: '获取个性化推荐结果失败',
                        type: 'error'
                    });
                }
            )

            //获取热门电影推荐
            ajaxPostJSON(
                this.urls.getRecentTopMovies,
                null,
                function (result) {
                    if (result.code === 'success') {
                        result.data.resultList.forEach(function (r) {
                            app.topMovies.push(r)
                        })
                    } else
                        app.$message({
                            message: '服务器错误，原因\n' + result.data,
                            type: 'error'
                        });
                },
                function () {
                    app.$message({
                        message: '获取热门电影推荐失败',
                        type: 'error'
                    });
                }
            )

            this.page.show = true;
            return;
        }
        this.$message({
            message: "请登录",
            type: 'error'
        });
        setTimeout(function () {
            window.open("./welcome.html", "_self")
        }, 2000);
    },
    computed: {
        movieArray: function () {
            let ret = [[], [], []];
            let movies = this.table.searchResult;
            for (let i = 0; i < movies.length; i++) {
                if (i <= 3)
                    ret[0].push(movies[i]);
                else if (i <= 7)
                    ret[1].push(movies[i]);
                else
                    ret[2].push(movies[i]);
            }
            return ret;
        },
        showRelated: function () {
            return this.dialog.relatedMovies.length > 0;
        }
    }
})