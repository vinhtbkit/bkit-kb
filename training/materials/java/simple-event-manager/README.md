# Assignment: a simple event management system

### Description
- Thiết kế một hệ thống quản lý các event một cách đơn giản 

### Requirement
- Hệ thống sẽ đọc các command từ input và sẽ thực hiện các xử lý tương ứng cần thiết
  - `schedule`: tạo một event đơn giản. Cú pháp của command là: `schedule <eventType> <eventName> <date>`. Trong đó:
    - `eventType`: bao gồm "MEETING", "CONFERENCE", "WEBMINAR"
    - `eventName`: tên của event
    - `date`: ngày diễn ra event, ko được phép là thời điểm trong quá khứ. Cú pháp: YYYY-MM-DD
  - `list <limit>`: list ra danh sách các event theo thứ tự sắp diễn ra
    - `limit`: Số event sẽ hiển thị tối đa. Nếu ko có thì sẽ hiển thị tất cả event

### Acceptance Criteria
- Sử dụng một loại collection hay map bất kỳ để lưu thông tin event: List, Map, Set,...
- Phải có xử lý thông báo lỗi khi người dùng nhập sai cú pháp input
- Sử dụng enum để đặc tả Event
- Tạo một interface có 2 method: `scheduleEvent`  và `listEvent`, sau đó implement interface này để sử dụng

