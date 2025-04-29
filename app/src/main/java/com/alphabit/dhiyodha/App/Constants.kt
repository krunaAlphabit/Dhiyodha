package com.alphabit.dhiyodha.App

object Constants {

    /**
     * Notification
     */
    const val NOTIFICATION_CHANNEL_ID = "Dhiyodha_channel"
    const val NOTIFICATION_CHANNEL_NAME = "Dhiyodha Messages"

    interface RequestPermission {
        companion object {
            const val REQUEST_GALLERY = 1000
            const val REQUEST_CAMERA = 1001
            const val REQUEST_AUDIO = 1002
            const val REQUEST_VIDEO = 1003
            const val REQUEST_CODE = 100
            const val REQUEST_PROFILE_PICTURE = 1004
        }
    }

    interface RequestCode {
        companion object {
            const val REQUEST_FROM_EMAIL_ADAPTER = 1002
            const val REQUEST_FROM_PHONE = 1003
            const val REQUEST_FROM_CONTACT_REQUEST_ADAPTER = 1004
        }
    }

    interface VerifyType {
        companion object {
            const val REQUEST_LOGIN_REQUEST = "LOGIN_REQUEST"
            const val REQUEST_REGISTRATION_REQUEST = "REGISTRATION_REQUEST"
            const val REQUEST_RESET_PASSWORD = "RESET_PASSWORD"
            const val REQUEST_MOBILE_VERIFICATION = "MOBILE_VERIFICATION_REQUEST"
            const val REQUEST_CHANGE_REQUEST = "change_request"
        }
    }

    interface TimeOut {
        companion object {
            const val SPLASH_TIME_OUT = 2000
            const val IMAGE_UPLOAD_CONNECTION_TIMEOUT = 500
            const val IMAGE_UPLOAD_SOCKET_TIMEOUT = 500
            const val SOCKET_TIME_OUT = 500
            const val CONNECTION_TIME_OUT = 60
        }
    }

    interface DateFormat {
        companion object {
            const val DATE_INPUT_FORMAT = "yyyy-mm-dd"
            const val YEAR_OUTPUT_FORMAT = "yyyy"
            const val DAY_OUTPUT_FORMAT = "dd"
            const val MONTH_OUTPUT_FORMAT = "MM"
        }
    }


    interface Urlpath {

        companion object {

            const val BASE_URL = "http://13.233.184.35:8085/dhiyodha-ecommerce/api/"

            const val SIGNUP_USER = "api/v1/auth/signup"
            const val LOGIN_USER = "api/v1/auth/login"
            const val FETCH_PRODUCT_BY_CATEGORY = "product/fetchProductByCategory"
            const val ADD_ITEM_INTO_WISHLIST = "wishlist/add"
            const val ADD_ITEM_INTO_CART = "cart/addToCart"
        }
    }

    interface ErrorClass {
        companion object {
            const val CODE = "code"
            const val STATUS = "status"
            const val MESSAGE = "message"
            const val DEVELOPER_MESSAGE = "developerMessage"
        }
    }

    interface ResponseCode {
        companion object {
            const val CODE_200 = 200
            const val CODE_204 = 204
            const val CODE_500 = 500
            const val CODE_400 = 400
            const val CODE_401 = 401
            const val CODE_403 = 403
            const val CODE_404 = 404
            const val CODE_422 = 422
            const val CODE_428 = 428
            const val CODE_429 = 429
        }
    }
}