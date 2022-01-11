1. Các bước thêm 1 màn hình mới 
   - Tạo 1 fragment + ViewModel trong package ui 
   - Thêm fragment vào trong navigation graph 
   - Tạo các action đến/đi cho fragment này
   - Thêm các argument cần thiết 
   - Dùng NavController class để di chuyển giữa các fragment 

2. Tạo object ViewModel bằng ViewModel Factory 
3. Thiết lập Data binding giữa Layout, ViewModel, Fragment 
   	- Loại bỏ findViewByID
   	- Access dữ liệu trực tiếp của model trong layout 
   	- ko cần viết code xử lý sự kiện của View trong Fragment mà trong ViewModel luôn 

4. Thiết lập data binding giữa LiveData & ViewModel

   - quan sát dữ liệu LiveData trong ViewModel thay đổi thì thực hiện action tương ứng (nếu cần)
   - Live Data: view được tự động cập nhật khi dữ liệu thay đổi

5. Tạo bảng trong CSDL với Room trong package database, viết unit test cho DatabaseDao 

6. Đọc/ghi CSDL trong ViewModel với coroutines, chú ý huỷ job coroutines trong onCleared của ViewModel 

   - Dữ liệu db có thể được wrap trong LiveData, khi dữ liệu trong DB thay đổi thì object LiveData cũng tự động cập nhật 

7. Hiển thị dữ liệu ở RecyclerView tối ưu với DiffUtil, Data binding 

   - DiffUtil dùng để cập nhật list 1 cách hiệu quả, tìm item nào bị thêm, sửa, xoá để cập nhật 
   - Kết hợp DiffUtil với ListAdapter,  gọi submitList() để detect phần tử nào được thêm, sửa, xoá 
   - 

   - Sử dụng Data binding kết hợp với RecyclerView
     - Tạo extension function
     - thêm binding trong layout xml của item 

8. Handle sự kiện (click) 
   - Trong activity/fragment: định nghĩa hàm xử lý sự kiện trong file layout, link tới hàm trong ViewModel 
   - Trong item của RecyclerView: 
     - attach click listener trong ViewHolder
     - xử lý click trong ViewModel (ko lắng nghe trong fragment/activity/ViewHolder )

9. Call API với Retrofit & coroutines: ko cần viết kiểu callback để handle exception 

- Sử dụng `CoroutineCallAdapterFactory` thay thế cho `Call` với coroutine `Deferred`.
- Sử dụng `await()` với `Deferred` object để chờ dữ liệu response trả về sẵn sàng mà ko block current thread
- https://proandroiddev.com/create-retrofit-calladapter-for-coroutines-to-handle-response-as-states-c102440de37a

10. Dùng Moshi thay cho Gson 

(https://www.reddit.com/r/androiddev/comments/684flw/why_use_moshi_over_gson/)

11. Thiết lập Repository, cache data từ network  

- Best practice: Tạo các object network, domain, database riêng *(follow theo nguyên lý separation of concerns*). Nếu network response hoặc db schema thay đổi -> ta có thể thay đổi thành phần tương ứng mà ko phải update toàn bộ code 
- Domain object: plain Kotlin data class - là các object đc hiển thị trên màn hình, xử lý trong app 
- Database object: object map với local db 
- Network object: object để parse/prepare network call 
- Giữa các object sẽ có method convert để chuyển đổi 
- Không lấy data từ network mỗi khi app launch mà hiển thị dữ liệu từ db trước để làm giảm thời gian loading app 
- Khi call API lấy dữ liêu, lưu trữ dữ liệu vào db trước khi hiển thị dữ liệu ngay lập tức 
- Repository pattern: 
  - tách biệt phần data source (local db, API, cache) với phần còn lại của app 
  - cung cấp API truy cập data 
  - chứa logic quyết định việc lấy dữ liệu từ API hay local db 
  - dễ mockup và test 



So far 

- Thêm repository, cache data  

- Load ảnh 

- Dependency injection với Dagger2/Koin 

- Viết unit test cho ViewModel/UI ...

  

https://proandroiddev.com/no-more-livedata-in-your-repository-there-are-better-options-25a7557b0730















https://developer.android.com/jetpack/docs/guide

https://github.com/android/architecture-components-samples

https://github.com/android/architecture-samples

https://codelabs.developers.google.com/codelabs/android-navigation/#0

https://codelabs.developers.google.com/codelabs/android-dagger/index.html#0

https://codelabs.developers.google.com/codelabs/android-paging/#1

https://github.com/googlecodelabs/android-testing

![img](https://images.viblo.asia/214bd36e-2e00-4473-9f09-2855d5e84637.png)