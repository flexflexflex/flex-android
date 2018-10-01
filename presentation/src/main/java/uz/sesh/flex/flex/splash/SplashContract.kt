package uz.sesh.flex.flex.splash

interface SplashContract{
    interface View{
        fun openMainActivity()
        fun openProfileFill()
        fun showLoading(show:Boolean)
        fun showNoInternetConnection(show: Boolean)
    }
    interface Presenter{
        fun checkUserProfileFill()
    }
}