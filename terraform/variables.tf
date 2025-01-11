variable "subscription" {
  type    = string
  default = "5aa2d64f-3669-4812-ab00-b732e79d8ebb"
}

variable "res" {
  type    = string
  default = "project_kube"
}

variable "location" {
  type    = string
  default = "France Central"
}

variable "cluster_name" {
  type        = string
  default     = "kube"
  description = "AKS name in Azure"
}

variable "kubernetes_version" {
  type        = string
  default     = "1.30.6"
  description = "Kubernetes version"
}

variable "system_node_count" {
  type        = number
  default     = 2
  description = "Number of AKS worker nodes"
}

variable "node_resource_group" {
  type        = string
  default     = "agentsRG"
  description = "RG name for cluster resources in Azure"
}