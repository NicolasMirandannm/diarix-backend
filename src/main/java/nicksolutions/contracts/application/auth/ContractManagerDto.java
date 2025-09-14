package nicksolutions.contracts.application.auth;

public record ContractManagerDto(
    String id,
    String name,
    String email,
    String password,
    String phoneNumber,
    Long version
) {
}
