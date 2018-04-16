# native-RenderScript
RenderScript demo using gradle+CMAKE

If you encounter the following problem：
`undefined reference to typeinfo for android::RSC::BaseObj`

Solution: Add {} to the virtual member function declaration of the BaseObj class. such as:

``` C++
/**
  * Base class for all RenderScript objects. Not for direct use by developers.
  */
class BaseObj : public android::RSC::LightRefBase<BaseObj> {
public:
    void * getID() const;
    virtual ~BaseObj(){};
    virtual void updateFromNative(){};
    virtual bool equals(const sp<const BaseObj>& obj){};

protected:
    void *mID;
    RS* mRS;
    const char * mName;

    BaseObj(void *id, sp<RS> rs);
    void checkValid();

    static void * getObjID(const sp<const BaseObj>& o);

};
```
