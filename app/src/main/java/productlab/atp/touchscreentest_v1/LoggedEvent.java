package productlab.atp.touchscreentest_v1;

/**
 * Created by Chelsea on 5/6/2016.
 * <p/>
 * This class creates and calls information about events logged by the touch listeners to be included
 * in the logger database.
 */
public class LoggedEvent {
    //private variables
    int _id;
    String _subject_number;
    String _admin_id;
    Long _time;
    Float _raw_x;
    Float _raw_y;
    Float _pressure;
    Integer _event_type;
    String _listener_id;
    String _gesture_type;
    Integer _action_masked;
    Integer _square_dim;
    Integer _img_dim;
    Integer _touch_dim;
    Integer _delegate_dim;
    Float _ratio;
    Integer _active_button;
    Float _space_dim;
    Integer _master_index;
    Float _size;

    // Empty constructor
    public LoggedEvent() {
    }

    // constructor
    public LoggedEvent(String _subject_number, String _admin_id, Long _time, Float _pressure, Float _raw_x, Float _raw_y, Integer _event_type,
                       String _listener_id, String _gesture_type, Integer _action_masked, Integer _square_dim, Integer _img_dim,
                       Integer _touch_dim, Integer _delegate_dim, Float _ratio, Integer _active_button, Float _space_dim, Integer _master_index,
                       Float _size) {
        this._subject_number = _subject_number;
        this._admin_id = _admin_id;
        this._time = _time;
        this._raw_x = _raw_x;
        this._raw_y = _raw_y;
        this._pressure = _pressure;
        this._event_type = _event_type;
        this._listener_id = _listener_id;
        this._gesture_type = _gesture_type;
        this._action_masked = _action_masked;
        this._square_dim = _square_dim;
        this._img_dim = _img_dim;
        this._touch_dim = _touch_dim;
        this._delegate_dim = _delegate_dim;
        this._ratio = _ratio;
        this._active_button = _active_button;
        this._space_dim = _space_dim;
        this._master_index = _master_index;
        this._size = _size;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting subject number
    public String getSubjectNumber() {
        return this._subject_number;
    }

    // setting subject number
    public void setSubjectNumber(String subject_number) {
        this._subject_number = subject_number;
    }


    // getting admin id
    public String getAdminId() {
        return this._admin_id;
    }

    // setting admin id
    public void setAdminId(String admin_id) {
        this._admin_id = admin_id;
    }

    // getting time
    public Long getTime() {
        return this._time;
    }

    // setting time
    public void setTime(Long time) {
        this._time = time;
    }

    // getting x
    public Float getRawX() {
        return this._raw_x;
    }

    // setting x
    public void setRawX(Float raw_x) {
        this._raw_x = raw_x;
    }

    // getting y
    public Float getRawY() {
        return this._raw_y;
    }

    // setting y
    public void setRawY(Float y) {
        this._raw_y = y;
    }

    // getting pressure
    public Float getPressure() {
        return this._pressure;
    }

    // setting pressure
    public void setPressure(Float pressure) {
        this._pressure = pressure;
    }

    // getting event type
    public Integer getEventType() {
        return this._event_type;
    }

    // setting event type
    public void setEventType(Integer event_type) {
        this._event_type = event_type;
    }

    // getting listener id
    public String getListenerId() {
        return this._listener_id;
    }

    // setting listener id
    public void setListenerId(String listener_id) {
        this._listener_id = listener_id;
    }

    // getting gesture type
    public String getGestureType() {
        return this._gesture_type;
    }

    // setting gesture type
    public void setGestureType(String gesture_type) {
        this._gesture_type = gesture_type;
    }

    // getting action type
    public Integer getActionMasked() {
        return this._action_masked;
    }

    // setting gesture type
    public void setActionMasked(Integer action_masked) {
        this._action_masked = action_masked;
    }

    // getting square dim
    public Integer getSquareDim() {
        return this._square_dim;
    }

    // setting square dim
    public void setSquareDim(Integer square_dim) {
        this._square_dim = square_dim;
    }

    // getting image dim
    public Integer getImgDim() {
        return this._img_dim;
    }

    // setting image dim
    public void setImgDim(Integer img_dim) {
        this._img_dim = img_dim;
    }

    // getting touch dim
    public Integer getTouchDim() {
        return this._touch_dim;
    }

    // setting touch dim
    public void setTouchDim(Integer touch_dim) {
        this._touch_dim = touch_dim;
    }

    // getting delegate dim
    public Integer getDelegateDim() {
        return this._delegate_dim;
    }

    // setting event type
    public void setDelegateDim(Integer delegate_dim) {
        this._delegate_dim = delegate_dim;
    }

    // getting ratio
    public Float getRatio() {
        return this._ratio;
    }

    // setting ratio
    public void setRatio(Float ratio) {
        this._ratio = ratio;
    }

    // getting active button
    public Integer getActiveButton() {
        return this._active_button;
    }

    // setting event type
    public void setActiveButton(Integer active_button) {
        this._active_button = active_button;
    }

    // getting space dim
    public Float getSpaceDim() {
        return this._space_dim;
    }

    // setting space dim
    public void setSpaceDim(Float space_dim) {
        this._space_dim = space_dim;
    }

    // getting master index
    public Integer getMasterIndex() {
        return this._master_index;
    }

    // setting master index
    public void setMasterIndex(Integer master_index) {
        this._master_index = master_index;
    }

    // getting size dim
    public Float getSize() {
        return this._size;
    }

    // setting space dim
    public void setSize(Float size_dim) {
        this._size = size_dim;
    }

}
