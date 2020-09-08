let app = new Vue({
    el: '#app',
    data: {
        urls: {
            login: 'http://localhost:8666/api/user/login',
            register: 'http://localhost:8666/api/user/register',
            isNameUnused: 'http://localhost:8666/api/user/isNameUnused',
            test: 'http://localhost:8666/api/movie/getMovieDetails?userName='
        },
        activeName: 'login',
        userName: '',
        password: '',
        confirmPassword: '',
        fullScreenLoading: false
    },
    methods: {
        login: function () {
            let app = this;
            let data = {
                userName: app.userName,
                password: app.password
            }
            app.fullScreenLoading = true;
            ajaxPostJSON(
                app.urls.login,
                data,
                function (result) {
                    app.fullScreenLoading = false;
                    if (result.code === 'success') {
                        if (result.data === 'User not exist')
                            app.$message({
                                message: '用户不存在',
                                type: 'error'
                            });
                        if (result.data === 'Wrong password')
                            app.$message({
                                message: '密码错误',
                                type: 'error'
                            });
                        if (result.data === 'Success') {
                            app.$message({
                                message: '登陆成功',
                                type: 'success'
                            });
                            setSessionStorage('userName', app.userName);

                            setTimeout(function () {
                                window.open("./homepage.html", "_self");
                            }, 2000);
                        }
                    } else
                        app.$message({
                            message: '服务器错误，原因\n' + result.data,
                            type: 'error'
                        });
                },
                function () {
                    app.fullScreenLoading = false;
                    app.$message({
                        message: '未知错误',
                        type: 'error'
                    });
                })
        },
        register: function () {
            let app = this;
            if (app.confirmPassword !== app.password) {
                app.$message({
                    message: '请确认密码',
                    type: 'error'
                });
                return;
            }
            let data = {
                userName: app.userName,
                password: app.password
            }
            app.fullScreenLoading = true;
            ajaxPostJSON(
                app.urls.register,
                data,
                function (result) {
                    app.fullScreenLoading = false;
                    if (result.code === 'success') {
                        if (result.data === true) {
                            //视为登录成功
                            app.$message({
                                message: '注册成功，即将跳转',
                                type: 'success'
                            });
                            setSessionStorage('userName', app.userName);

                            setTimeout(function () {
                                window.open("./homepage.html", "_self");
                            }, 2000);
                        } else
                            app.$message({
                                message: '注册失败',
                                type: 'error'
                            });
                    } else
                        app.$message({
                            message: '服务器错误，原因\n' + result.data,
                            type: 'error'
                        });
                },
                function () {
                    app.fullScreenLoading = false;
                    app.$message({
                        message: '未知错误',
                        type: 'error'
                    });
                }
            )
        },
        handleTabClick: function () {
            this.userName = '';
            this.password = '';
            this.confirmPassword = '';
        }
    },
    created: function () {
        if (getSessionStorage('userName') !== null) {
            this.$message({
                message: "您已登录，将跳转到首页",
                type: 'error'
            });
            setTimeout(function () {
                window.open("./homepage.html", "_self")
            }, 2000);
        }
    }
})