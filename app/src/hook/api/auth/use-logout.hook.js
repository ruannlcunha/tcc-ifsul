import { axiosInstance } from "../_base/axios-instance.api";

export async function logout({}) {
  await axiosInstance.post("/logout",);
}
