export class TesteResponse<T> {
    content: Array<T>;
    pageNumber: number;
    pageSize: number;
    totalPages: number;
    totalElements: number;
    numberOfElements: number;
    first: boolean;
    last: boolean;
}
