import { axiosInstance } from "../../../api/_base/axios-instance.api";

export function useUpload() {
  async function cadastro({ nome, email, senha }) {
    const response = await axiosInstance.post(
      "/usuarios",
      {
        nome,
        email,
        senha
      }
    );
    return response.data;
  }

  return { cadastro };
}
