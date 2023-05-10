package org.example;

import jcuda.*;
import jcuda.driver.*;
import static jcuda.driver.JCudaDriver.*;

public class JCudaAccessTime
{
    private CUcontext context;
    private final CUdevice device;
    private CUdeviceptr d_data;
    private final Pointer h_data;

    public JCudaAccessTime()
    {
        JCudaDriver.setExceptionsEnabled(true);
        cuInit(0);
        device = new CUdevice();
        cuDeviceGet(device, 0);
        h_data = Pointer.to(new float[1]);
    }

    public void initialize()
    {
        context = new CUcontext();
        cuCtxCreate(context, 0, device);

        d_data = new CUdeviceptr();
        cuMemAlloc(d_data, Sizeof.FLOAT);
    }

    public void run()
    {
        cuMemcpyHtoD(d_data, h_data, Sizeof.FLOAT);
        cuMemcpyDtoH(h_data, d_data, Sizeof.FLOAT);
    }

    public void cleanup()
    {
        cuMemFree(d_data);
        cuCtxDestroy(context);
    }
}
